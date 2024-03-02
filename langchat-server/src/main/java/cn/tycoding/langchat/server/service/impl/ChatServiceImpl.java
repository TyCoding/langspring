package cn.tycoding.langchat.server.service.impl;

import cn.tycoding.langchat.biz.entity.LcMessage;
import cn.tycoding.langchat.biz.entity.LcOss;
import cn.tycoding.langchat.biz.mapper.OssMapper;
import cn.tycoding.langchat.biz.service.MessageService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.core.dto.*;
import cn.tycoding.langchat.core.enums.FileEnum;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.utils.StreamEmitter;
import cn.tycoding.langchat.server.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final LangChatService langChatService;
    private final MessageService messageService;
    private final OssMapper ossMapper;

    @Override
    public void chat(ChatReq req) {
        Flux<ChatResponse> flux = langChatService.stream(req);
        handler(flux, req);
    }

    private void handler(Flux<ChatResponse> flux, ChatReq req) {
        StreamEmitter emitter = req.getEmitter();
        long startTime = System.currentTimeMillis();
        AtomicLong usage = new AtomicLong();
        StringBuilder text = new StringBuilder();
        flux.subscribe(
                res -> {
                    String str = res.getResult().getOutput().getContent();
                    usage.addAndGet(res.getMetadata().getUsage().getPromptTokens());
                    text.append(str);
                    req.getEmitter().send(new ChatRes(str));
                },
                err -> {
                    System.err.println("Error: " + err.getMessage());
                    emitter.send("Error: " + err.getMessage());
                    emitter.complete();
                    throw new ServiceException(err.getMessage());
                },
                () -> {
                    // save message
                    if (req.getConversationId() != null) {
                        req.setMessage(text.toString());
                        LcMessage message = new LcMessage();
                        BeanUtils.copyProperties(req, message);
                        message.setRole(RoleEnum.ASSISTANT.getName());
                        messageService.addMessage(message);
                    }

                    emitter.send(new ChatRes(usage.get(), startTime));
                    emitter.complete();
                }
        );
    }

    @Override
    public void stream(TextR req) {
//        langChatService.stream(req);
    }

    @Override
    public String text(TextR req) {
        return langChatService.text(req);
    }

    @Override
    public LcOss image(ImageR req) {
        OssR ossR = langChatService.image(req);

        LcOss oss = new LcOss();
        BeanUtils.copyProperties(ossR, oss);
//        oss.setUserId(ClientUtil.getClientId());
        oss.setChannel(FileEnum.OUTPUT.getChannel());
        ossMapper.insert(oss);
        return oss;
    }
}

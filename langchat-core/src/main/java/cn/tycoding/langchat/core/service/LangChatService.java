package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.core.dto.ChatReq;
import cn.tycoding.langchat.core.dto.ImageR;
import cn.tycoding.langchat.core.dto.OssR;
import cn.tycoding.langchat.core.dto.TextR;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author tycoding
 * @since 2024/1/29
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangChatService {

    private final OllamaChatClient chatClient;

    public Flux<ChatResponse> stream(ChatReq req) {
        return chatClient.stream(req.getPrompt());
    }

    public String text(TextR req) {
        return chatClient.call(req.getPrompt()).getResult().getOutput().getContent();
    }

    public OssR image(ImageR req) {
        return null;
    }
}

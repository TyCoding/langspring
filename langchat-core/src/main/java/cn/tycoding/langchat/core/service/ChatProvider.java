package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.core.dto.ChatReq;
import cn.tycoding.langchat.core.enums.SpringBeanEnum;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/3/2
 */
@Component
@AllArgsConstructor
public class ChatProvider {

    private final ApplicationContext applicationContext;
    private final OllamaChatClient chatClient;
    private final OpenAiChatClient openAiChatClient;

    public ChatClient get(ChatReq req) {
        String model = req.getModel();
        if (model == null) {
            return openAiChatClient;
        }
        if (SpringBeanEnum.OLLAMA.getName().equalsIgnoreCase(model)) {
            return chatClient;
        }
        if (SpringBeanEnum.OPENAI.getName().equalsIgnoreCase(model)) {
            return openAiChatClient;
        }
        return openAiChatClient;
    }

}

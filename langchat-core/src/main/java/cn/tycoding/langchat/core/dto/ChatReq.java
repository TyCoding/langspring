package cn.tycoding.langchat.core.dto;

import cn.tycoding.langchat.core.utils.StreamEmitter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.prompt.Prompt;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@Data
@Accessors(chain = true)
public class ChatReq {

    private String message;

    private String conversationId;

    private String chatId;

    private String promptId;

    private Prompt prompt;

    private StreamEmitter emitter;

    public ChatReq() {
    }

    public ChatReq(StreamEmitter emitter) {
        this.emitter = emitter;
    }

    public ChatReq(Prompt prompt) {
        this.prompt = prompt;
    }

    public ChatReq(Prompt prompt, StreamEmitter emitter) {
        this.prompt = prompt;
        this.emitter = emitter;
    }

    public ChatReq(String message, StreamEmitter emitter) {
        this.message = message;
        this.emitter = emitter;
    }
}

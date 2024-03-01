package cn.tycoding.langchat.core.utils;

import cn.tycoding.langchat.core.enums.PromptConst;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

/**
 * @author tycoding
 * @since 2024/3/1
 */
public class PromptUtil {

    public static Prompt build(String message) {
        return new Prompt(message);
    }

    public static Prompt build(String message, String promptText) {
        return new PromptTemplate(promptText).create(Map.of(PromptConst.TMP, message));
    }
}

package cn.tycoding.langchat.core.enums;

/**
 * @author tycoding
 * @since 2024/1/8
 */
public enum ChatModelEnum {

    OPENAI("openai"),
    GLM("glm"),
    ;

    private String name;

    ChatModelEnum(String name) {
        this.name = name;
    }
}

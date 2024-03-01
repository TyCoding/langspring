package cn.tycoding.langchat.core.properties;

import cn.tycoding.langchat.core.enums.ChatModelEnum;
import cn.tycoding.langchat.core.enums.ModelNameEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/1/8
 */
@Data
@ConfigurationProperties(prefix = "langchat")
public class LangChatProps {

    /**
     * 对话模型
     */
    private ChatModelEnum chatModel;
    /**
     * 模型名称
     */
    private ModelNameEnum modelName;
    /**
     * URL
     */
    private String url;
    /**
     * Proxy
     */
    private String proxy;
}

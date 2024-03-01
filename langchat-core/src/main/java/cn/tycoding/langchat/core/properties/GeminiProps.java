package cn.tycoding.langchat.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.gemini")
public class GeminiProps {

    private String project;
    private String location;
    private String modelName;
    private Float temperature;
    private Integer maxOutputTokens;
    private Integer topK;
    private Float topP;
}

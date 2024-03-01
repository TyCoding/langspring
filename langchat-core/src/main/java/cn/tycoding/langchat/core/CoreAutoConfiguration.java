package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.OssProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Configuration
@EnableConfigurationProperties({OssProps.class, LangChatProps.class})
public class CoreAutoConfiguration {

}

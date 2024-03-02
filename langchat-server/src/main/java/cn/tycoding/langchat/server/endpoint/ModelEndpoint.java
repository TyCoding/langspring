package cn.tycoding.langchat.server.endpoint;

import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.enums.SpringBeanEnum;
import lombok.AllArgsConstructor;
import org.springframework.ai.autoconfigure.bedrock.anthropic.BedrockAnthropicChatProperties;
import org.springframework.ai.autoconfigure.bedrock.cohere.BedrockCohereChatProperties;
import org.springframework.ai.autoconfigure.bedrock.llama2.BedrockLlama2ChatProperties;
import org.springframework.ai.autoconfigure.bedrock.titan.BedrockTitanChatProperties;
import org.springframework.ai.autoconfigure.ollama.OllamaChatProperties;
import org.springframework.ai.autoconfigure.openai.OpenAiChatProperties;
import org.springframework.ai.autoconfigure.vertexai.VertexAiChatProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/3/2
 */
@RequestMapping("/langchat/model")
@RestController
@AllArgsConstructor
public class ModelEndpoint {

    private final ApplicationContext applicationContext;

    @GetMapping("/list")
    public R list() {
        List<String> list = new ArrayList<>();
        if (applicationContext.containsBean(SpringBeanEnum.OLLAMA.getPath())) {
            OllamaChatProperties props = applicationContext.getBean(OllamaChatProperties.class);
            list.add(props.getModel());
        }
        if (applicationContext.containsBean(SpringBeanEnum.OPENAI.getPath())) {
            OpenAiChatProperties props = applicationContext.getBean(OpenAiChatProperties.class);
            list.add(props.getOptions().getModel());
        }
//        if (applicationContext.containsBean(SpringAIBeanConst.HUGGINGFACE)) {
//            HuggingfaceChatProperties props = applicationContext.getBean(HuggingfaceChatProperties.class);
//            list.add(props.getOptions());
//        }
//        if (applicationContext.containsBean(SpringAIBeanConst.AZURE_OPENAI)) {
//            AzureOpenAiChatProperties props = applicationContext.getBean(AzureOpenAiChatProperties.class);
//            list.add(props.getOptions());
//        }
        if (applicationContext.containsBean(SpringBeanEnum.VERTEXAI.getPath())) {
            VertexAiChatProperties props = applicationContext.getBean(VertexAiChatProperties.class);
            list.add(props.getModel());
        }
        if (applicationContext.containsBean(SpringBeanEnum.BEDROCK_ANTHROPIC.getPath())) {
            BedrockAnthropicChatProperties props = applicationContext.getBean(BedrockAnthropicChatProperties.class);
            list.add(props.getModel());
        }
        if (applicationContext.containsBean(SpringBeanEnum.BEDROCK_COHERE.getPath())) {
            BedrockCohereChatProperties props = applicationContext.getBean(BedrockCohereChatProperties.class);
            list.add(props.getModel());
        }
        if (applicationContext.containsBean(SpringBeanEnum.BEDROCK_LLAMA2.getPath())) {
            BedrockLlama2ChatProperties props = applicationContext.getBean(BedrockLlama2ChatProperties.class);
            list.add(props.getModel());
        }
        if (applicationContext.containsBean(SpringBeanEnum.BEDROCK_TITAN.getPath())) {
            BedrockTitanChatProperties props = applicationContext.getBean(BedrockTitanChatProperties.class);
            list.add(props.getModel());
        }

        return R.ok(list);
    }
}

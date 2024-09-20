package cn.fo9c.educationaltourism.springAi;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 这是一个关于Spring AI的功能集合
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    // 设置私有语言模型，注入ZhiPuAiChatModel对象
    private final ZhiPuAiChatModel chatModel;

    @Autowired
    public ChatController(ZhiPuAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 简单的调用 AI ChatModel 生成回复
     * @param message 传入的消息
     * @return AI回复（可设置初始值）
     */
    @GetMapping("/call")
    public String generate(@RequestParam(value = "message", defaultValue = "举例一个伟人,说明他的事迹") String message) {
        // 调用chatModel对象的call方法，传入message参数
        return chatModel.call(message);
    }

    @GetMapping("/ai/generate1")
    public String generate1(@RequestParam(value = "message", defaultValue = "举例一个伟人,说明他的事迹") String message) {
        // 创建PromptTemplate对象，设置语句模板
        PromptTemplate promptTemplate = new PromptTemplate("告诉我一个关于{thing}的{about}。");
        Prompt tellSomething = promptTemplate.create(Map.of("thing", "猫", "about", "有趣的事情"));
        System.out.println(chatModel.call(tellSomething).getResult().getOutput().getContent());
//        return chatModel.call(tellSomething).getResults();
        return chatModel.call(message);
    }



    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}
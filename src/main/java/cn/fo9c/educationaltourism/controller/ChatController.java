package cn.fo9c.educationaltourism.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    // 设置私有语言模型，注入ZhiPuAiChatModel对象
    private final ZhiPuAiChatModel chatModel;

    @Autowired
    public ChatController(ZhiPuAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai/generate")
    public List<Generation> generate(@RequestParam(value = "message", defaultValue = "举例一个伟人,说明他的事迹") String message) {
        // 创建PromptTemplate对象，设置语句模板
        PromptTemplate promptTemplate = new PromptTemplate("告诉我一个关于{thing}的{about}。");
        Prompt tellSomething = promptTemplate.create(Map.of("thing", "猫", "about", "有趣的事情"));

        return chatModel.call(tellSomething).getResults();
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}
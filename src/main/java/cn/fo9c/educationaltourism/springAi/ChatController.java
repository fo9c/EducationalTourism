package cn.fo9c.educationaltourism.springAi;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.AssistantPromptTemplate;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * 这是一个关于Spring AI的功能集合
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    // 设置私有语言模型，注入ZhiPuAiChatModel对象
//    private final ZhiPuAiChatModel chatModel;
//
//    @Autowired
//    public ChatController(ZhiPuAiChatModel chatModel) {
//        this.chatModel = chatModel;
//    }

    private final OllamaChatModel chatModel;

    @Autowired
    public ChatController(OllamaChatModel ollamaChatModel) {
        this.chatModel = ollamaChatModel;
    }

    /**
     * 简单的调用 AI ChatModel 生成回复
     * @param message 传入的消息（可设置初始值）
     * @return AI回复
     */
    @GetMapping("/call")
    public String generate(@RequestParam(value = "message", defaultValue = "举例一个伟人,说明他的事迹") String message) {
        // 调用chatModel对象的call方法，传入String类型的message
        System.out.println("Message: " + message);
        return chatModel.call(message);
    }

    /**
     * 使用PromptTemplate对象创建基础的语言模板
     * @param body 传入的消息
     * @return AI回复
     */
    @GetMapping("/PT")
    public Generation promptTemplate(@RequestParam(value = "body", defaultValue = "猫") String body) {
        // 创建PromptTemplate对象，设置语句模板
        PromptTemplate promptTemplate = new PromptTemplate("告诉我一个关于{thing}的{about}。");

        // 调用PromptTemplate对象的create方法，传入Map类型的message
        Prompt prompt = promptTemplate.create(Map.of("thing", body, "about", "有趣的事情"));

        System.out.println("Prompt: " + prompt.getContents());
        return chatModel.call(prompt).getResult();
    }

    /**
     * 使用SystemPromptTemplate对象创建前置Prompt
     * @param name 用户名
     * @return AI回复
     */
    @GetMapping("/systemPT")
    public List<Generation> systemPT(@RequestParam(value = "name", defaultValue = "aalo") String name) {
        // 创建UserMessage对象，设置用户基本消息
        String userText = """
        tell me a joke about dog.
        """;
        Message userMessage = new UserMessage(userText);

        String systemText = """
        user`s name is {name}。
        please speak "hello，!!" + User`s name at the begin of your answer,and speak"meow~"at the begin of your answer.
        """;

        SystemPromptTemplate systemPT = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPT.createMessage(Map.of("name", name));

        // 创建Prompt对象，设置用户和系统的基本消息
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        System.out.println("UserMessage: " + userMessage.getContent());
        System.out.println("SystemMessage: " + systemMessage.getContent());
        return chatModel.call(prompt).getResults();
    }

    /**
     * 生成流
     *
     * @return AI回复（可设置初始值）
     */
    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}
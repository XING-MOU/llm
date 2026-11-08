package com.xing.llm.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/model")
public class ChatModelController {

    @Autowired
    private DashScopeChatModel dashScopeChatModel;

    @RequestMapping("/call/string")
    public String callString(String message) {
        return dashScopeChatModel.call(message);
    }

    @RequestMapping("/call/messages")
    public String callMessages(String message) {
        SystemMessage systemMessage = new SystemMessage("you are a excellent translator");
        Message userMsg = new UserMessage(message);
        return dashScopeChatModel.call(systemMessage, userMsg);
    }

    @RequestMapping("/call/prompt")
    public String callPrompt(String message) {
        SystemMessage systemMessage = new SystemMessage("请如实回答我的问题");
        Message userMsg = new UserMessage(message);

        ChatOptions chatOptions = ChatOptions.builder().model("deepseek-v3").build();
        Prompt prompt=new Prompt.Builder().messages(systemMessage, userMsg).chatOptions(chatOptions).build();
        return dashScopeChatModel.call(prompt).getResult().getOutput().getText();
    }

    @RequestMapping(value = "/stream/string", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> callStreamString(String message) {
        return dashScopeChatModel.stream(message);
    }
}

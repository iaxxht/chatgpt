package com.xht.controller;

import com.xht.component.ChatGPT;
import com.xht.component.ChatGPTStream;
import com.xht.entity.chat.ChatCompletion;
import com.xht.entity.chat.ChatCompletionResponse;
import com.xht.entity.chat.Message;
import com.xht.listener.ConsoleStreamListener;
import com.xht.listener.SseStreamListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("/chat")
public class ChatGPTController {

    @RequestMapping("/toChat")
    @ResponseBody
    public Message getChat(HttpServletRequest request){
        String type= request.getParameter("type");
        String answer= request.getParameter("answer");
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .timeout(3000)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        Message system = Message.ofSystem(type);
        Message message = Message.of(answer);

        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .messages(Arrays.asList(system, message))
                .maxTokens(3000)
                .temperature(0.9)
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        System.out.println(res);
        return res;
    }

    @RequestMapping("/toChat1")
    public void getChat1(HttpServletRequest request, HttpServletResponse response){
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .apiKey("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")
                .timeout(3000)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        String type= request.getParameter("type");
        String answer= request.getParameter("answer");

        Message system = Message.ofSystem(type);
        Message message = Message.of(answer);

        ConsoleStreamListener listener = new ConsoleStreamListener();
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(Arrays.asList(system, message))
                .build();
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
    }

    @RequestMapping("/toChat2")
    @ResponseBody
    public SseEmitter getChat2(HttpServletRequest request, HttpServletResponse response){
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .apiKey("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")
                .timeout(3000)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        SseEmitter sseEmitter = new SseEmitter(-1L);
        String answer= request.getParameter("answer");
        SseStreamListener listener = new SseStreamListener(sseEmitter);
        Message message = Message.of(answer);

        listener.setOnComplate(msg -> {
            //回答完成，可以做一些事情
            System.out.println(msg);
            sseEmitter.complete();
        });
        chatGPTStream.streamChatCompletion(Arrays.asList(message), listener);
        return sseEmitter;
    }
}

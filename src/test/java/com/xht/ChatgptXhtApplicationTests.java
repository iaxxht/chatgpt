package com.xht;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ChatgptXhtApplicationTests {

    @Test
    void contextLoads() {
//        Proxy proxy = Proxys.http("127.0.0.1", 1080);
//
//        ChatGPT chatGPT = ChatGPT.builder()
//                .apiKey("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")
//                .timeout(900)
//                .apiHost("https://api.openai.com/") //代理地址
//                .build()
//                .init();
//        CreditGrantsResponse response1 = chatGPT.creditGrants();
//        log.info("余额：{}", response1.getTotalAvailable());
//
//
//        Message system = Message.ofSystem("你现在是一个诗人，专门写七言绝句");
//        Message message = Message.of("写一段七言绝句诗，题目是：火锅！");
//
//        ChatCompletion chatCompletion = ChatCompletion.builder()
//                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//                .messages(Arrays.asList(system, message))
//                .maxTokens(3000)
//                .temperature(0.9)
//                .build();
//        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
//        Message res = response.getChoices().get(0).getMessage();
//        System.out.println(res);
    }

}

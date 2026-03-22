package com.ai.ollama.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient ollamaChatClient;
    private final ChatClient openAiChatClient;

    public ChatController(
            @Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
            @Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.ollamaChatClient = ollamaChatClient;
        this.openAiChatClient = openAiChatClient;
    }

    @GetMapping("/chat/ollama")
    public String chatWithOllama(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }

    @GetMapping("/chat/openAi")
    public String chatWithOpenAi(@RequestParam("message") String message) {
        return openAiChatClient.prompt(message).call().content();
    }
}

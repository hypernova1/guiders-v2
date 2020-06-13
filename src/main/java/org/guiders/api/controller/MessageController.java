package org.guiders.api.controller;

import org.guiders.api.payload.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public Message greeting(Message.Receiver receiver) {
        return new Message();
    }

}
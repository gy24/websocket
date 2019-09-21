package com.gy.demowebsocket.controller;

import com.gy.demowebsocket.bean.Chat;
import com.gy.demowebsocket.bean.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
public class GreetingController {

    @Resource
    SimpMessagingTemplate simpMessagingTemplate;

   /* @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public Message greeting(Message message) {
        return message;
    }*/
   //与上边是等价的
   @MessageMapping("/hello")
   public void greeting(Message message) {
       simpMessagingTemplate.convertAndSend("/topic/greeting",message);
   }



   @MessageMapping("/chat")
   public void chat(Principal principal, Chat chat){
       chat.setFrom(principal.getName());
       simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
   }



}

package com.projetospring.chatonline.modules.message.aplication.controllers;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.message.domain.cases.SendMessageCase;
import com.projetospring.chatonline.modules.user.domain.User;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class MessageSocketController {

    @Autowired
    private SendMessageCase sendMessageCase;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId,
                            @Payload @Valid SendMenssageDto sendMenssageDto,
                            org.springframework.messaging.Message<?> message) {

        User user = (User) message.getHeaders().get("simpUser");

        sendMessageCase.execute(sendMenssageDto, user);

        messagingTemplate.convertAndSend("/topic/chat/" + roomId, sendMenssageDto);
    }
}

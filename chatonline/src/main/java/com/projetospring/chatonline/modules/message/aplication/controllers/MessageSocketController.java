package com.projetospring.chatonline.modules.message.aplication.controllers;

import com.projetospring.chatonline.core.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.core.security.UserAuthenticationService;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.message.aplication.dtos.SendMessageCommand;
import com.projetospring.chatonline.modules.message.domain.cases.SendMessageCase;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import java.security.Principal;

@Controller
public class MessageSocketController {

    @Autowired
    private SendMessageCase sendMessageCase;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId,
                            @Payload SendMenssageDto sendMenssageDto,
                            Principal principal) {

        UserEntity user = userAuthenticationService.getUserFromPrincipal(principal);

        SendMessageCommand sendMessageCommand = new SendMessageCommand(sendMenssageDto, user);
        sendMessageCase.execute(sendMessageCommand);

        messagingTemplate.convertAndSend("/topic/chat/" + roomId, sendMenssageDto);
    }

    @MessageExceptionHandler(PermissionUserInvalidException.class)
    public void handlePermissionError(PermissionUserInvalidException ex, Principal principal) {
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/errors", ex.getMessage());
    }
}

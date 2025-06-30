package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.ResponseDto;
import com.projetospring.chatonline.dtos.SendMessageDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.service.cases.SendMessageCase;

import jakarta.validation.Valid;

@RestController
public class MessageController {

	@Autowired
	private SendMessageCase sendMessageCase;

	@MessageMapping("/api/chat/sendMessage")
	public ResponseEntity<ResponseDto> sendMessage(@RequestBody @Valid SendMessageDto sendMessageDto,
			Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		sendMessageCase.execute(sendMessageDto, userDetails.getUser());

		return ResponseEntity.accepted().body(new ResponseDto(201, "Message sent successfully", null));
	}

}

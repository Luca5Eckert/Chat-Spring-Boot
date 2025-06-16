package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.MessageDto;
import com.projetospring.chatonline.service.SendMessageCase;

import jakarta.validation.Valid;

@RestController
public class MessageController {

	@Autowired
	private SendMessageCase sendMessageCase;

	@MessageMapping("/api/chat/{room_id}/sendMessage")
	public ResponseEntity<?> sendMessage(@RequestBody @Valid MessageDto message) {
		sendMessageCase.execute(message);
		return ResponseEntity.accepted().body("Message sent successfully");
	}

}

package com.projetospring.chatonline.modules.message.aplication.controllers;

import com.projetospring.chatonline.core.security.UserAuthenticationService;
import com.projetospring.chatonline.modules.message.aplication.dtos.DeleteMessageCommand;
import com.projetospring.chatonline.modules.message.aplication.dtos.EditMessageCommand;
import com.projetospring.chatonline.modules.message.aplication.dtos.EditMessageDto;
import com.projetospring.chatonline.modules.message.domain.Message;
import com.projetospring.chatonline.modules.message.domain.cases.DeleteMessageCase;
import com.projetospring.chatonline.modules.message.domain.cases.EditMessageCase;
import com.projetospring.chatonline.modules.message.domain.cases.ListMessagesByRoomCase;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.message.domain.cases.SendMessageCase;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/message")
public class MessageRestController {

	@Autowired
	private EditMessageCase editMessageCase;

	@Autowired
	private DeleteMessageCase deleteMessageCase;

	@Autowired
	private ListMessagesByRoomCase listMessagesByRoomCase;

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@PutMapping()
	public ResponseEntity<?> editMessage(@Valid @RequestBody EditMessageDto editMessageDto, Authentication authentication) {
		UserEntity userEntity = userAuthenticationService.getUserFromPrincipal(authentication);

		EditMessageCommand editMessageCommand = new EditMessageCommand(editMessageDto, userEntity);
		editMessageCase.execute(editMessageCommand);

		return ResponseEntity.ok("Message edited successfully");
	}

	@DeleteMapping("/{messageId}")
	public ResponseEntity<?> deleteMessage(@PathVariable UUID messageId, Authentication authentication) {
		UserEntity userEntity = userAuthenticationService.getUserFromPrincipal(authentication);

		DeleteMessageCommand deleteMessageCommand = new DeleteMessageCommand(messageId, userEntity);
		deleteMessageCase.execute(deleteMessageCommand);

		return ResponseEntity.ok("Message deleted successfully");
	}

	@GetMapping("/room/{roomId}")
	public ResponseEntity<List<Message>> listMessagesByRoom(@PathVariable UUID roomId) {
		List<Message> messages = listMessagesByRoomCase.execute(roomId);
		return ResponseEntity.ok(messages);
	}

}

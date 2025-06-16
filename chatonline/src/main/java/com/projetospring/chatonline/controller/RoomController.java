package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.CreateRoomDto;
import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.service.CreateRoomCase;
import com.projetospring.chatonline.service.DeleteRoomCase;

import jakarta.validation.Valid;

@RestController
public class RoomController {

	@Autowired
	private CreateRoomCase createRoomCase;

	@Autowired
	private DeleteRoomCase deleteRoomCase;

	@PostMapping("/api/room/create")
	public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto, Authentication authentication) {
		User userSend = (User) authentication.getCredentials();
		createRoomCase.execute(createRoomDto, userSend);
		return ResponseEntity.ok("Add room with successfully");
	}

	@PostMapping("/api/room/delete")
	public ResponseEntity<?> deleteRoom(@Valid @RequestBody RoomDto roomDto, Authentication authentication) {
		deleteRoomCase.execute(roomDto);
		return ResponseEntity.ok("Delete room with successfully");
	}

}

package com.projetospring.chatonline.modules.room.aplication.controllers;

import com.projetospring.chatonline.core.security.UserAuthenticationService;
import com.projetospring.chatonline.modules.room.aplication.dtos.CreateRoomCommand;
import com.projetospring.chatonline.modules.room.aplication.dtos.DeleteRoomCommand;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.projetospring.chatonline.modules.room.aplication.dtos.CreateRoomDto;
import com.projetospring.chatonline.modules.room.aplication.dtos.RoomDto;
import com.projetospring.chatonline.modules.room.domain.cases.CreateRoomCase;
import com.projetospring.chatonline.modules.room.domain.cases.DeleteRoomCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room")
public class RoomController {

	@Autowired
	private CreateRoomCase createRoomCase;

	@Autowired
	private DeleteRoomCase deleteRoomCase;

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@PostMapping()
	public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto, Authentication authentication) {
		UserEntity userSend = userAuthenticationService.getUserFromPrincipal(SecurityContextHolder.getContext().getAuthentication());

		CreateRoomCommand createRoomCommand = new CreateRoomCommand(createRoomDto, userSend);
		createRoomCase.execute(createRoomCommand);

		return ResponseEntity.ok("Add room with successfully");
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteRoom(@Valid @RequestBody RoomDto roomDto, Authentication authentication) {
		UserEntity userEntity = userAuthenticationService.getUserFromPrincipal(authentication);

		DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(roomDto, userEntity);
		deleteRoomCase.execute(deleteRoomCommand);

		return ResponseEntity.ok("Delete room with successfully");
	}

}

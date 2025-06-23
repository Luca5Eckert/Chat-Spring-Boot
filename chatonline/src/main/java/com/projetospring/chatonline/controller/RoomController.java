package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.CreateRoomDto;
import com.projetospring.chatonline.dtos.ResponseDto;
import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.model.Room;
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
	public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto,
			Authentication authentication) {
		UserDetailsImpl userSend = (UserDetailsImpl) authentication.getPrincipal();
		Room roomCreate = createRoomCase.execute(createRoomDto, userSend.getUser());
		
		return ResponseEntity.ok(new ResponseDto(201, "Add room with successfully", roomCreate));
	}

	@PostMapping("/api/room/delete")
	public ResponseEntity<ResponseDto> deleteRoom(@Valid @RequestBody RoomDto roomDto, Authentication authentication) {
		String nameRoom = deleteRoomCase.execute(roomDto);
		
		return ResponseEntity.ok(new ResponseDto(201, "Delete room with successfully", nameRoom));
	}

}

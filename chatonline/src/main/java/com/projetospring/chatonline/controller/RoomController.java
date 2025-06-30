package com.projetospring.chatonline.controller;

import com.projetospring.chatonline.dtos.EnterRoomDto;
import com.projetospring.chatonline.service.cases.EnterRoomCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.SecurityContextDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.CreateRoomDto;
import com.projetospring.chatonline.dtos.ResponseDto;
import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.service.cases.CreateRoomCase;
import com.projetospring.chatonline.service.cases.DeleteRoomCase;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RoomController {

	private final CreateRoomCase createRoomCase;

	private final DeleteRoomCase deleteRoomCase;

	private final EnterRoomCase enterRoomCase;

	@PostMapping("api/room/enter")
	public ResponseEntity<ResponseDto> enterInRoom(@Valid @RequestBody EnterRoomDto enterRoomDto){
		UserDetailsImpl userSend = getUserDetails();
		enterRoomCase.execute(enterRoomDto, userSend.getUser());

		return ResponseEntity.ok(new ResponseDto(201, "Room access permission", null));
	}

	public ResponseEntity<ResponseDto> addUserInRoom(@Valid @RequestBody EnterRoomCase enterInTheRoomCase){
		return null;
	}


	@PostMapping("/api/room/create")
	public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto) {
		UserDetailsImpl userSend = getUserDetails();
		RoomDto roomCreate = createRoomCase.execute(createRoomDto, userSend.getUser());
		
		return ResponseEntity.ok(new ResponseDto(201, "Add room with successfully", roomCreate));
	}

	@PostMapping("/api/room/delete")
	public ResponseEntity<ResponseDto> deleteRoom(@Valid @RequestBody RoomDto roomDto, Authentication authentication) {
		String nameRoom = deleteRoomCase.execute(roomDto);
		
		return ResponseEntity.ok(new ResponseDto(201, "Delete room with successfully", nameRoom));
	}

	private UserDetailsImpl getUserDetails(){
		return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}


}

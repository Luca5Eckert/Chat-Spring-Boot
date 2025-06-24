package com.projetospring.chatonline.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.CreateRoomDto;
import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateRoomCase {

	@Autowired
	private RoomRepository repository;

	@Transactional
	public RoomDto execute(CreateRoomDto createRoomDto, User userSend) {
		var roomModel = dtoToModel(createRoomDto, userSend);
		repository.save(roomModel);
		return new RoomDto(roomModel);
	}

	public Room dtoToModel(CreateRoomDto createRoomDto, User userSend) {
		return Room.createRoom(null, createRoomDto.name(), createRoomDto.type(), 0, LocalDateTime.now(),
				createRoomDto.description(), userSend);
	}
}

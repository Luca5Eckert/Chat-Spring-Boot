package com.projetospring.chatonline.modules.room.domain.cases;

import java.time.LocalDateTime;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.room.aplication.dtos.CreateRoomDto;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;

@Service
public class CreateRoomCase {

	@Autowired
	private RoomRepository repository;

	public void execute(CreateRoomDto createRoomDto, UserEntity userSend) {
		var roomModel = dtoToModel(createRoomDto, userSend);
		repository.save(roomModel);
	}

	public Room dtoToModel(CreateRoomDto createRoomDto, UserEntity userSend) {
		return Room.createRoom(null, createRoomDto.name(), createRoomDto.type(), 0, LocalDateTime.now(),
				createRoomDto.description(), userSend);
	}
}

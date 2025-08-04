package com.projetospring.chatonline.modules.room.domain.cases;

import java.time.LocalDateTime;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.dtos.CreateRoomCommand;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.room.aplication.dtos.CreateRoomDto;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;

@Service
public class CreateRoomCase implements UseCase<CreateRoomCommand, Void> {

	@Autowired
	private final RoomRepository repository;

    public CreateRoomCase(RoomRepository repository) {
        this.repository = repository;
    }

    public Void execute(CreateRoomCommand createRoomCommand) {
		var roomModel = dtoToModel(createRoomCommand);
		repository.save(roomModel);
		return null;
	}

	public Room dtoToModel(CreateRoomCommand createRoomCommand) {
		return Room.createRoom(null, createRoomCommand.createRoomDto().name(), createRoomCommand.createRoomDto().type(), 0, LocalDateTime.now(),
				createRoomCommand.createRoomDto().description(), createRoomCommand.user());
	}

}

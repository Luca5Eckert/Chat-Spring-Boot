package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.exceptions.RoomNotFoundDatabaseException;
import com.projetospring.chatonline.exceptions.UserDontCanDeleteRoomException;
import com.projetospring.chatonline.exceptions.UserStatusRoomDontFoundException;
import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.model.UserStatusRoomId;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class DeleteRoomCase {

	private final RoomRepository repository;

	private final UserStatusRoomRepository userStatusRoomRepository;

    public DeleteRoomCase(RoomRepository repository, UserStatusRoomRepository userStatusRoomRepository) {
        this.repository = repository;
        this.userStatusRoomRepository = userStatusRoomRepository;
    }

    @Transactional
	public String execute(RoomDto roomDto, User user) {
		Room room = repository.findById(roomDto.id()).orElseThrow(()-> new RoomNotFoundDatabaseException("The aplication can't found the room"));

		UserStatusRoom userStatusRoom = getUserStatus(user,room);

		if(!userStatusRoom.canDeleteRoom()){
			throw new UserDontCanDeleteRoomException("The user does not have permission to delete the room. To do so, you need to be an administrator");
		}

		repository.deleteById(room.getId());

		return roomDto.name();
	}

	private UserStatusRoom getUserStatus(User user, Room room) {
		UserStatusRoomId userStatus = new UserStatusRoomId(user,room);

		return userStatusRoomRepository.findById(userStatus).orElseThrow(()-> new UserStatusRoomDontFoundException("The aplication can't found the user status"));
	}

	private void deleteById(RoomDto roomDto) {
		repository.deleteById(roomDto.id());
	}

}

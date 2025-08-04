package com.projetospring.chatonline.modules.message.domain.cases;

import java.time.LocalDateTime;
import java.util.Optional;

import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.core.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.modules.message.domain.Message;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.User;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.UserStatusRoomRepository;

import jakarta.validation.Valid;

@Service
public class SendMessageCase {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private UserStatusRoomRepository repositoryUserStatus;

	@Autowired
	private RoomRepository roomRepository;

	public void execute(@Valid SendMenssageDto sendMenssageDto, User user) {
		Room sendForRoom = roomRepository.findById(sendMenssageDto.roomId())
				.orElseThrow(() -> new RuntimeException("Room not found"));

		checkPermissionUser(sendForRoom, user);

		var message = dtoToMessage(sendMenssageDto.content(), user, sendForRoom);

		repository.save(message);
	}

	public void checkPermissionUser(Room room, User user) {
		UserStatusRoom statusRoom = getStatusUser(room, user).orElseThrow(
				() -> new PermissionUserInvalidException("Error in found the association between user and room"));

		if(!statusRoom.canSendMessage()){
			throw new PermissionUserInvalidException("User don't have the permission to send message in this room");
		}


	}

	public Optional<UserStatusRoom> getStatusUser(Room room, User user) {
		UserStatusRoomId id = new UserStatusRoomId(user, room);
		return repositoryUserStatus.findById(id);
	}

	public Message dtoToMessage(String content, User user, Room room) {
		return Message.createMessage(null, user, room, LocalDateTime.now(),
				content);
	}
}

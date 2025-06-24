package com.projetospring.chatonline.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RoomIdDto;
import com.projetospring.chatonline.dtos.SendMessageDto;
import com.projetospring.chatonline.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.exceptions.RoomDatabaseException;
import com.projetospring.chatonline.model.Message;
import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.model.UserStatusRoomId;
import com.projetospring.chatonline.repository.MessageRepository;
import com.projetospring.chatonline.repository.RoomRepository;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class SendMessageCase {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserStatusRoomRepository repositoryUserStatus;

	@Autowired
	private RoomRepository roomRepository;

	@Transactional
	public void execute(@Valid SendMessageDto sendMenssageDto, User user) {
		Room room = getRoomFromDatabase(sendMenssageDto.sendFor())
				.orElseThrow(() -> new RoomDatabaseException("The aplication can't found the room"));

		checkPermissionUser(room, user);

		var message = dtoToMessage(sendMenssageDto, room, user);

		messageRepository.save(message);
	}

	public Optional<Room> getRoomFromDatabase(RoomIdDto roomIdDto) {
		return roomRepository.findById(roomIdDto.id());
	}

	public void checkPermissionUser(Room room, User user) {
		UserStatusRoom statusRoom = getStatusUser(room, user).orElseThrow(
				() -> new PermissionUserInvalidException("Error in found the association between user and room"));

		if (!statusRoom.canSendMessage()) {
			throw new PermissionUserInvalidException("User don't have the permission to send message in this room");
		}

	}

	public Optional<UserStatusRoom> getStatusUser(Room room, User user) {
		UserStatusRoomId id = new UserStatusRoomId(user, room);
		return repositoryUserStatus.findById(id);
	}

	public Message dtoToMessage(SendMessageDto sendMenssageDto, Room room, User user) {
		return Message.createMessage(null, user, room, LocalDateTime.now(), sendMenssageDto.content());
	}
}

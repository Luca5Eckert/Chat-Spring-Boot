package com.projetospring.chatonline.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.SendMenssageDto;
import com.projetospring.chatonline.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.model.Message;
import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.model.UserStatusRoomId;
import com.projetospring.chatonline.repository.MessageRepository;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;

import jakarta.validation.Valid;

@Service
public class SendMessageCase {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private UserStatusRoomRepository repositoryUserStatus;

	public void execute(@Valid SendMenssageDto sendMenssageDto, User user) {
		checkPermissionUser(sendMenssageDto.sendFor(), user);
		var message = dtoToMessage(sendMenssageDto, user);
		repository.save(message);
	}

	public void checkPermissionUser(Room room, User user) {
		UserStatusRoom statusRoom = getStatusUser(room, user).orElseThrow(
				() -> new PermissionUserInvalidException("Error in found the association between user and room"));

		var typeAcess = statusRoom.getRoomAccess();

		switch (typeAcess) {
		case BLOCKED ->
			throw new PermissionUserInvalidException("User don't have the permission to send message in this room");
		}

	}

	public Optional<UserStatusRoom> getStatusUser(Room room, User user) {
		UserStatusRoomId id = new UserStatusRoomId(user, room);
		return repositoryUserStatus.findById(id);
	}

	public Message dtoToMessage(SendMenssageDto sendMenssageDto, User user) {
		return Message.createMessage(null, user, sendMenssageDto.sendFor(), LocalDateTime.now(),
				sendMenssageDto.content());
	}
}

package com.projetospring.chatonline.modules.message.domain.cases;

import java.time.LocalDateTime;

import com.projetospring.chatonline.modules.message.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.message.domain.validator.PermissionValidatorService;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.message.domain.Message;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;

import jakarta.validation.Valid;

@Service
public class SendMessageCase {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private PermissionValidatorService permissionValidatorService;

	@Autowired
	private RoomRepository roomRepository;

	public void execute(@Valid SendMenssageDto sendMenssageDto, UserEntity user) {
		Room sendForRoom = roomRepository.findById(sendMenssageDto.roomId())
				.orElseThrow(() -> new RuntimeException("Room not found"));

		permissionValidatorService.checkPermission(user, sendForRoom, PermissionType.SEND_MESSAGE);

		var message = dtoToMessage(sendMenssageDto.content(), user, sendForRoom);

		repository.save(message);
	}

	public Message dtoToMessage(String content, UserEntity user, Room room) {
		return Message.createMessage(null, user, room, LocalDateTime.now(),
				content);
	}
}

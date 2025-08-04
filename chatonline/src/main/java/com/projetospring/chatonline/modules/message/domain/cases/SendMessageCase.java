package com.projetospring.chatonline.modules.message.domain.cases;

import java.time.LocalDateTime;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.message.aplication.dtos.SendMessageCommand;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
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
public class SendMessageCase implements UseCase<SendMessageCommand, Void> {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private PermissionValidatorService permissionValidatorService;

	@Autowired
	private RoomRepository roomRepository;

	public Void execute(@Valid SendMessageCommand sendMessageCommand) {
		Room sendForRoom = roomRepository.findById(sendMessageCommand.sendMenssageDto().roomId())
				.orElseThrow(() -> new RuntimeException("Room not found"));

		permissionValidatorService.checkPermission(sendMessageCommand.userEntity(), sendForRoom, PermissionType.SEND_MESSAGE);

		var message = dtoToMessage(sendMessageCommand.sendMenssageDto().content(), sendMessageCommand.userEntity(), sendForRoom);

		repository.save(message);
		return null;
	}

	public Message dtoToMessage(String content, UserEntity user, Room room) {
		return Message.createMessage(null, user, room, LocalDateTime.now(),
				content);
	}
}

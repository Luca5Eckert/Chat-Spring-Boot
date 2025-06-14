package com.projetospring.chatonline.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.MessageDto;
import com.projetospring.chatonline.model.Message;
import com.projetospring.chatonline.repository.MessageRepository;

@Service
public class SendMessageCase {

	@Autowired
	private MessageRepository repository;

	public void execute(MessageDto messageDto) {
		var message = dtoToMessage(messageDto);
		repository.save(message);
	}

	public void checkPermissionUser() {
		
	}
	
	public Message dtoToMessage(MessageDto messageDto) {
		return Message.createMessage(null, messageDto.sendBy(), messageDto.sendFor(), LocalDateTime.now(),
				messageDto.content());
	}
}

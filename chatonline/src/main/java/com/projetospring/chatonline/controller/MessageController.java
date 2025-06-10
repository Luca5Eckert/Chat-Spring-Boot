package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.service.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	private final MessageService service;
	
	public MessageController(MessageService service) {
		this.service = service;
	}
	
	
}

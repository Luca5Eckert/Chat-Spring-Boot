package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.projetospring.chatonline.repository.MessageRepository;

@Controller
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	
}

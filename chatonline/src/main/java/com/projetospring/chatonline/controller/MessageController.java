package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.repository.MessageRepository;

@RestController
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	
}

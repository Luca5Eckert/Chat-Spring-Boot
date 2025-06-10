package com.projetospring.chatonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;
	
	
}

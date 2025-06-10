package com.projetospring.chatonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private final RoomRepository repository;

	public RoomService(RoomRepository repository) {
		this.repository = repository;
	}
	
	
}

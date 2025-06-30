package com.projetospring.chatonline.service.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RoomDto;
import com.projetospring.chatonline.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class DeleteRoomCase {

	@Autowired
	private RoomRepository repository;

	@Transactional
	public String execute(RoomDto roomDto) {
		deleteById(roomDto);
		return roomDto.name();
	}

	private void deleteById(RoomDto roomDto) {
		repository.deleteById(roomDto.id());
	}

}

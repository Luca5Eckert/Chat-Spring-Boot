package com.projetospring.chatonline.modules.room.domain.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.room.aplication.dtos.RoomDto;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;

@Service
public class DeleteRoomCase {
    
    @Autowired
    private RoomRepository repository;

    public void execute(RoomDto roomDto){
        deleteById(roomDto);
    }

    private void deleteById(RoomDto roomDto){
        repository.deleteById(roomDto.id());
    }

}

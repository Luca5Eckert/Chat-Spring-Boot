package com.projetospring.chatonline.modules.room.aplication.services;

import com.projetospring.chatonline.modules.room.aplication.exceptions.RoomNotFoundException;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.domain.Room;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getRoom(UUID id){
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
    }

}

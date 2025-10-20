package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.exceptions.RoomNotFoundException;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.domain.Room;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetRoomByIdCase implements UseCase<UUID, Room> {

    private final RoomRepository repository;

    public GetRoomByIdCase(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room execute(UUID roomId) {
        return repository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));
    }
}

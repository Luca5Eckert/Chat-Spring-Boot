package com.projetospring.chatonline.modules.room.aplication.repository;

import com.projetospring.chatonline.modules.room.domain.Room;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import java.util.List;

public interface RoomRepository {

    Optional<Room> findById(UUID idRoom);

    List<Room> findAll();

    Room save(Room room);

    void deleteById(UUID idRoom);

    boolean existsById(UUID idRoom);

}

package com.projetospring.chatonline.modules.room.aplication.repository;


import com.projetospring.chatonline.modules.room.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoomRepositoryImpl implements RoomRepository{

    private final JpaRoomRepository jpaRoomRepository;

    public RoomRepositoryImpl(JpaRoomRepository jpaRoomRepository) {
        this.jpaRoomRepository = jpaRoomRepository;
    }

    @Override
    public Optional<Room> findById(UUID idRoom) {
        return jpaRoomRepository.findById(idRoom);
    }

    @Override
    public List<Room> findAll() {
        return jpaRoomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return jpaRoomRepository.save(room);
    }

    @Override
    public void deleteById(UUID idRoom) {
        jpaRoomRepository.deleteById(idRoom);
    }

    @Override
    public boolean existsById(UUID idRoom) {
        return jpaRoomRepository.existsById(idRoom);
    }
}

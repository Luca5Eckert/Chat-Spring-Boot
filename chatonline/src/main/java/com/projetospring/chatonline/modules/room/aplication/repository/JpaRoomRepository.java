package com.projetospring.chatonline.modules.room.aplication.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetospring.chatonline.modules.room.domain.Room;

@Repository
public interface JpaRoomRepository extends JpaRepository<Room, UUID>  {

    public void deleteById(UUID id); 
}

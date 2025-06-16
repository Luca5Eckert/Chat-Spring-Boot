package com.projetospring.chatonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetospring.chatonline.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID>  {

    public void deleteById(UUID id); 
}

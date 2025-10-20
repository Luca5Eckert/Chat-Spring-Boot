package com.projetospring.chatonline.modules.message.aplication.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetospring.chatonline.modules.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MessageRepository{

    Optional<Message> findById(UUID idRoom);

    List<Message> findAll();

    Message save(Message message);

    void deleteById(UUID idMessage);

    boolean existsById(UUID idMessage);

    List<Message> findByRoomId(UUID roomId);

}

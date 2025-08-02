package com.projetospring.chatonline.modules.message.aplication.repository;

import java.util.UUID;

import com.projetospring.chatonline.modules.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,UUID>{

}

package com.projetospring.chatonline.modules.message.aplication.repository;

import com.projetospring.chatonline.modules.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaMessageRepository extends JpaRepository<Message, UUID> {
}

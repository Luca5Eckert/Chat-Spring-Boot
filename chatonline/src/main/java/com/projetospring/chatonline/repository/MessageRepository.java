package com.projetospring.chatonline.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetospring.chatonline.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,UUID>{

}

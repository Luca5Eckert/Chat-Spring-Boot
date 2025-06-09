package com.projetospring.chatonline.repository;

import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.projetospring.chatonline.model.Message;

public class MessageRepository implements Repository<UUID,Message>{

}

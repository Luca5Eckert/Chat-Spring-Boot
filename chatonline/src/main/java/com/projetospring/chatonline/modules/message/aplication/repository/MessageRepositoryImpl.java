package com.projetospring.chatonline.modules.message.aplication.repository;

import com.projetospring.chatonline.modules.message.domain.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MessageRepositoryImpl implements MessageRepository {

    private final JpaMessageRepository jpaMessageRepository;

    public MessageRepositoryImpl(JpaMessageRepository jpaMessageRepository) {
        this.jpaMessageRepository = jpaMessageRepository;
    }

    @Override
    public Optional<Message> findById(UUID idRoom) {
        return jpaMessageRepository.findById(idRoom);
    }

    @Override
    public List<Message> findAll() {
        return jpaMessageRepository.findAll();
    }

    @Override
    public Message save(Message message) {
        return jpaMessageRepository.save(message);
    }

    @Override
    public void deleteById(UUID idMessage) {
        jpaMessageRepository.deleteById(idMessage);
    }

    @Override
    public boolean existsById(UUID idMessage) {
        return jpaMessageRepository.existsById(idMessage);
    }

    @Override
    public List<Message> findByRoomId(UUID roomId) {
        return jpaMessageRepository.findAll().stream()
                .filter(message -> message.getSendFor().getId().equals(roomId))
                .toList();
    }
}

package com.projetospring.chatonline.modules.message.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.message.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListMessagesByRoomCase implements UseCase<UUID, List<Message>> {

    private final MessageRepository messageRepository;

    public ListMessagesByRoomCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> execute(UUID roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}

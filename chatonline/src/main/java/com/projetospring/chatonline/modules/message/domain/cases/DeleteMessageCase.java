package com.projetospring.chatonline.modules.message.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.message.aplication.dtos.DeleteMessageCommand;
import com.projetospring.chatonline.modules.message.aplication.exceptions.MessageNotFoundException;
import com.projetospring.chatonline.modules.message.aplication.exceptions.UnauthorizedMessageAccessException;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.message.domain.Message;
import org.springframework.stereotype.Service;

@Service
public class DeleteMessageCase implements UseCase<DeleteMessageCommand, Void> {

    private final MessageRepository messageRepository;

    public DeleteMessageCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Void execute(DeleteMessageCommand deleteMessageCommand) {
        Message message = messageRepository.findById(deleteMessageCommand.messageId())
                .orElseThrow(() -> new MessageNotFoundException("Message not found"));

        // Check if the user is the owner of the message
        if (!message.getSendBy().getId().equals(deleteMessageCommand.userEntity().getId())) {
            throw new UnauthorizedMessageAccessException("You can only delete your own messages");
        }

        messageRepository.deleteById(deleteMessageCommand.messageId());

        return null;
    }
}

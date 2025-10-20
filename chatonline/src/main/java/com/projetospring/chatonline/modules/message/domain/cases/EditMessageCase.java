package com.projetospring.chatonline.modules.message.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.message.aplication.dtos.EditMessageCommand;
import com.projetospring.chatonline.modules.message.aplication.exceptions.MessageNotFoundException;
import com.projetospring.chatonline.modules.message.aplication.exceptions.UnauthorizedMessageAccessException;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.message.domain.Message;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
import org.springframework.stereotype.Service;

@Service
public class EditMessageCase implements UseCase<EditMessageCommand, Void> {

    private final MessageRepository messageRepository;
    private final PermissionValidatorService permissionValidatorService;

    public EditMessageCase(MessageRepository messageRepository, PermissionValidatorService permissionValidatorService) {
        this.messageRepository = messageRepository;
        this.permissionValidatorService = permissionValidatorService;
    }

    @Override
    public Void execute(EditMessageCommand editMessageCommand) {
        Message message = messageRepository.findById(editMessageCommand.editMessageDto().messageId())
                .orElseThrow(() -> new MessageNotFoundException("Message not found"));

        permissionValidatorService.checkPermission(editMessageCommand.userEntity(), message.getSendFor(), PermissionType.EDIT_MESSAGE);

        // Additional check: User can only edit their own messages
        if (!message.getSendBy().getId().equals(editMessageCommand.userEntity().getId())) {
            throw new UnauthorizedMessageAccessException("You can only edit your own messages");
        }

        message.setContent(editMessageCommand.editMessageDto().content());
        messageRepository.save(message);

        return null;
    }
}

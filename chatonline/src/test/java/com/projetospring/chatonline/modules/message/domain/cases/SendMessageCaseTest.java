package com.projetospring.chatonline.modules.message.domain.cases;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.message.aplication.dtos.SendMessageCommand;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.message.domain.Message;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendMessageCaseTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private PermissionValidatorService permissionValidatorService;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private SendMessageCase sendMessageCase;

    private final UserEntity mockUser = new UserEntity();
    private final Room mockRoom = new Room();

    @Test
    void givenValidDto_whenExecute_thenSendMessageSuccessfully() {
        // Arrange
        UUID roomId = UUID.randomUUID();
        SendMenssageDto dto = new SendMenssageDto("Hello World", roomId);
        SendMessageCommand command = new SendMessageCommand(dto, mockUser);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(mockRoom));
        doNothing().when(permissionValidatorService).checkPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE);

        // Act
        sendMessageCase.execute(command);

        // Assert
        verify(permissionValidatorService).checkPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE);
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void givenNonExistentRoom_whenExecute_thenThrowException() {
        UUID roomId = UUID.randomUUID();
        SendMenssageDto dto = new SendMenssageDto("Fail", roomId);
        SendMessageCommand command = new SendMessageCommand(dto, mockUser);
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sendMessageCase.execute(command));
        verify(permissionValidatorService, never()).checkPermission(any(), any(), any());
        verify(messageRepository, never()).save(any());
    }

    @Test
    void givenNoPermission_whenExecute_thenThrowException() {
        UUID roomId = UUID.randomUUID();
        SendMenssageDto dto = new SendMenssageDto("No access", roomId);
        SendMessageCommand command = new SendMessageCommand(dto, mockUser);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(mockRoom));
        doThrow(new RuntimeException("Permission denied")).when(permissionValidatorService).checkPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE);

        assertThrows(RuntimeException.class, () -> sendMessageCase.execute(command));
        verify(permissionValidatorService).checkPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE);
        verify(messageRepository, never()).save(any());
    }
}

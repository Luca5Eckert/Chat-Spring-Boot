package com.projetospring.chatonline.modules.message.domain.validator;

import com.projetospring.chatonline.core.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.modules.message.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PermissionValidatorServiceTest {

    @Mock
    private PermissionChecker sendChecker;

    @Mock
    private PermissionChecker editChecker;

    @InjectMocks
    private PermissionValidatorService permissionValidatorService;

    private final User mockUser = new User();
    private final Room mockRoom = new Room();

    @Test
    void shouldReturnTrueWhenCheckerSupportsPermissionAndGrantsPermission() {
        when(sendChecker.supports(PermissionType.SEND_MESSAGE)).thenReturn(true);
        when(sendChecker.hasPermission(mockUser, mockRoom)).thenReturn(true);

        permissionValidatorService = new PermissionValidatorService(List.of(sendChecker));

        boolean result = permissionValidatorService.hasPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenNoCheckerSupportsPermission() {
        when(sendChecker.supports(PermissionType.EDIT_MESSAGE)).thenReturn(false);

        permissionValidatorService = new PermissionValidatorService(List.of(sendChecker));

        assertThrows(PermissionUserInvalidException.class, () ->
                permissionValidatorService.hasPermission(mockUser, mockRoom, PermissionType.EDIT_MESSAGE)
        );
    }

    @Test
    void shouldThrowExceptionWhenCheckerSaysNoPermission() {
        when(sendChecker.supports(PermissionType.SEND_MESSAGE)).thenReturn(true);
        when(sendChecker.hasPermission(mockUser, mockRoom)).thenReturn(false);

        permissionValidatorService = new PermissionValidatorService(List.of(sendChecker));

        assertThrows(PermissionUserInvalidException.class, () ->
                permissionValidatorService.checkPermission(mockUser, mockRoom, PermissionType.SEND_MESSAGE)
        );
    }
}

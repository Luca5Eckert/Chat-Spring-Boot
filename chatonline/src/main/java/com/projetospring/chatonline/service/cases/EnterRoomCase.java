package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.dtos.EnterRoomDto;
import com.projetospring.chatonline.exceptions.AccessDeniedEnterRoomException;
import com.projetospring.chatonline.exceptions.RoomNotFoundDatabaseException;
import com.projetospring.chatonline.model.*;
import com.projetospring.chatonline.repository.RoomRepository;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnterRoomCase {

    private final UserStatusRoomRepository userStatusRoomRepository;
    private final RoomRepository roomRepository;

    public EnterRoomCase(UserStatusRoomRepository userStatusRoomRepository, RoomRepository roomRepository) {
        this.userStatusRoomRepository = userStatusRoomRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void execute(@Valid EnterRoomDto enterRoomDto, User user) {
        Room room = findRoomById(enterRoomDto.roomIdDto().id());

        UserStatusRoomId userStatusRoomId = createUserStatusRoomId(user, room);

        UserStatusRoom userStatusRoom = checkUserPermissionAndRetrieveStatus(userStatusRoomId);

        updateUserRoomStatus(userStatusRoom,userStatusRoomId);
    }

    private void updateUserRoomStatus(UserStatusRoom userStatusRoom, UserStatusRoomId userStatusRoomId) {

        if (userStatusRoom != null) {
            userStatusRoom.setActive(true);

            userStatusRoomRepository.save(userStatusRoom);

        } else {
            UserStatusRoom newUserStatusRoom = new UserStatusRoom.UserStatusRoomBuilder(userStatusRoomId)
                    .setRoomAccess(TypeRoomAccess.NORMAL)
                    .setActive(true)
                    .build();

            userStatusRoomRepository.save(newUserStatusRoom);
        }
    }

    private Room findRoomById(UUID roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundDatabaseException("Room not found"));
    }

    private UserStatusRoomId createUserStatusRoomId(User user, Room room) {
        return new UserStatusRoomId(user, room);
    }

    private UserStatusRoom checkUserPermissionAndRetrieveStatus(UserStatusRoomId id) {
        Optional<UserStatusRoom> userStatusRoom = userStatusRoomRepository.findById(id);
        userStatusRoom.ifPresent(status -> {
                    if (!status.canEnterRoom()) {
                        throw new AccessDeniedEnterRoomException(
                                "User doesn't have permission to enter room: " + id.getRoom().getId()
                        );
                    }
                });
        return userStatusRoom.orElse(null);
    }
}
package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.dtos.EnterRoomDto;
import com.projetospring.chatonline.exceptions.AccessDeniedEnterRoomException;
import com.projetospring.chatonline.exceptions.RoomNotFoundDatabaseException;
import com.projetospring.chatonline.model.*;
import com.projetospring.chatonline.repository.RoomRepository;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnterRoomCase {

    private final UserStatusRoomRepository userStatusRoomRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public void execute(@Valid EnterRoomDto enterRoomDto, User user) {
        Room room = findRoomById(enterRoomDto.roomIdDto().id());

        UserStatusRoomId userStatusRoomId = createUserStatusRoomId(user, room);

        ensureUserCanEnterRoom(userStatusRoomId, user, room);

        updateUserRoomStatus(userStatusRoomId);
    }

    private void updateUserRoomStatus(UserStatusRoomId userStatusRoomId) {
        Optional<UserStatusRoom> userStatus = userStatusRoomRepository.findById(userStatusRoomId);

        if (userStatus.isPresent()) {
            UserStatusRoom existingStatus = userStatus.get();
            existingStatus.setActive(true);
            userStatusRoomRepository.save(existingStatus);
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

    private void ensureUserCanEnterRoom(UserStatusRoomId id, User user, Room room) {
        userStatusRoomRepository.findById(id)
                .ifPresent(status -> {
                    if (!status.canEnterInRoom()) {
                        throw new AccessDeniedEnterRoomException(
                                "User doesn't have permission to enter room: " + room.getId()
                        );
                    }
                });
    }
}
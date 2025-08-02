package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.dtos.AddUserRoomDto;
import com.projetospring.chatonline.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.exceptions.RoomNotFoundDatabaseException;
import com.projetospring.chatonline.exceptions.UserStatusRoomDontFoundException;
import com.projetospring.chatonline.model.Room;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.model.UserStatusRoomId;
import com.projetospring.chatonline.repository.RoomRepository;
import com.projetospring.chatonline.repository.UserRepository;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class AddUserToRoomCase {

    private final UserStatusRoomRepository userStatusRoomRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;


    public AddUserToRoomCase(UserStatusRoomRepository userStatusRoomRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.userStatusRoomRepository = userStatusRoomRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }


    public void execute(AddUserRoomDto addUserRoomDto, User userCreating){
        Room room = roomRepository.findById(addUserRoomDto.roomIdDto().id()).orElseThrow(() -> new RoomNotFoundDatabaseException("The aplication can't found the room"));

        checkIfUserCanAddUser(userCreating, room);

        checkIfUserCanBeAdd(addUserRoomDto);

    }

    private void checkIfUserCanBeAdd(AddUserRoomDto addUserRoomDto) {

    }

    private void checkIfUserCanAddUser(User userCreating, Room room) {
        UserStatusRoomId userStatusRoomId = new UserStatusRoomId(userCreating, room);

        UserStatusRoom userStatusRoom = userStatusRoomRepository.findById(userStatusRoomId).orElseThrow(() -> new UserStatusRoomDontFoundException("The aplication can't found the user status"));

        if( !userStatusRoom.haveAdministratorPermission() ){
            throw new PermissionUserInvalidException("User does not have the permission to add another user");
        }

    }

}

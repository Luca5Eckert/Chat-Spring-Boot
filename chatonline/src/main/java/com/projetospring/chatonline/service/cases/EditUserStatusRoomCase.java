package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.dtos.EditUserStatusRoomDto;
import com.projetospring.chatonline.exceptions.UserDontCanDeleteRoomException;
import com.projetospring.chatonline.exceptions.UserStatusRoomDontFoundException;
import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.repository.UserStatusRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class EditUserStatusRoomCase {

    private final UserStatusRoomRepository userStatusRoomRepository;

    public EditUserStatusRoomCase(UserStatusRoomRepository userStatusRoomRepository) {
        this.userStatusRoomRepository = userStatusRoomRepository;
    }

    public void execute(EditUserStatusRoomDto editUserStatusRoomDto){
        UserStatusRoom userStatusRoom = userStatusRoomRepository.findById(editUserStatusRoomDto.id()).orElseThrow(() -> new UserStatusRoomDontFoundException("The aplication can't found the user status"));

        if(!userStatusRoom.canEditRoom()){
            throw new UserDontCanDeleteRoomException("The user does not have permission to edit the room. To do so, you need to be an administrator");
        }

        editRoom(userStatusRoom, editUserStatusRoomDto);

        userStatusRoomRepository.save(userStatusRoom);
    }

    private void editRoom(UserStatusRoom userStatusRoom, EditUserStatusRoomDto editUserStatusRoomDto) {
        userStatusRoom.setRoomAccess(editUserStatusRoomDto.typeRoomAccess());
    }

}

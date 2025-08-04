package com.projetospring.chatonline.modules.userstatusroom.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.userstatusroom.aplication.dtos.AddUserToRoomCommand;
import org.springframework.stereotype.Service;

@Service
public class AddUserToRoomCase implements UseCase<AddUserToRoomCommand, Void> {

    @Override
    public Void execute(AddUserToRoomCommand addUserToRoomCommand) {
        return null;
    }
}

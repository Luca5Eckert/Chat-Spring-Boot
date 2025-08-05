package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.dtos.EditRoomDto;

public class EditRoomCase implements UseCase<EditRoomDto, Void> {


    @Override
    public Void execute(EditRoomDto editRoomCommand) {
        return null;
    }
}

package com.projetospring.chatonline.modules.room.domain.commands;

import com.projetospring.chatonline.core.enums.TypeEdit;
import com.projetospring.chatonline.modules.room.domain.Room;

public class EditDescriptionRoomCommand implements EditRoomCommand<String> {

    @Override
    public void execute(Room room, String s) {
        room.setDescription(s);
    }

    @Override
    public TypeEdit getType() {
        return TypeEdit.CHANGE_DESCRIPTION_ROOM;
    }
}

package com.projetospring.chatonline.modules.room.domain.commands;

import com.projetospring.chatonline.core.enums.TypeEdit;
import com.projetospring.chatonline.modules.room.domain.Room;

public class RenameRoomCommand implements EditRoomCommand<String> {


    @Override
    public void execute(Room room, String string) {
        room.setNameRoom(string);
    }

    @Override
    public TypeEdit getType() {
        return TypeEdit.RENAME_ROOM;
    }
}

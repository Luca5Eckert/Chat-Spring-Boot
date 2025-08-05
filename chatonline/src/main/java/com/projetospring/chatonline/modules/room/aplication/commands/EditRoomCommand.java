package com.projetospring.chatonline.modules.room.aplication.commands;

import com.projetospring.chatonline.core.command.EditCommand;
import com.projetospring.chatonline.core.enums.TypeEdit;
import com.projetospring.chatonline.modules.room.domain.Room;

public interface EditRoomCommand extends EditCommand<Room> {

    @Override
    public void execute(Room object);

    @Override
    public TypeEdit getType();
}

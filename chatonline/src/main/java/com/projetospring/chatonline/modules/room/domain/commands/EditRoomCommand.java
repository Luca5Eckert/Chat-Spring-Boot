package com.projetospring.chatonline.modules.room.domain.commands;

import com.projetospring.chatonline.core.command.EditCommand;
import com.projetospring.chatonline.core.enums.TypeEdit;
import com.projetospring.chatonline.modules.room.domain.Room;

public interface EditRoomCommand<T> extends EditCommand<Room, T> {


    @Override
    public void execute(Room object, T t);

    @Override
    public TypeEdit getType();
}

package com.projetospring.chatonline.modules.room.aplication.commands;

import com.projetospring.chatonline.core.command.EditCommand;
import com.projetospring.chatonline.core.enums.TypeEdit;
import com.projetospring.chatonline.modules.room.aplication.dtos.EditRoomDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EditCommandRoomFactory {

    Map<TypeEdit, EditRoomCommand> commands;

    public EditCommandRoomFactory(List<EditRoomCommand> commandsList){
        this.commands = commandsList.stream()
                .collect(Collectors.toMap(EditRoomCommand::getType, Function.identity()));
    }

    public EditCommand<?> toInstance(EditRoomDto editRoomDto){
        return commands.get(editRoomDto.typeEdit());
    }


}

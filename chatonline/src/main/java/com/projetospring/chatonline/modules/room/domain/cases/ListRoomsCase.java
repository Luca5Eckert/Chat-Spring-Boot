package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.domain.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRoomsCase implements UseCase<Void, List<Room>> {

    private final RoomRepository repository;

    public ListRoomsCase(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> execute(Void input) {
        return repository.findAll();
    }
}

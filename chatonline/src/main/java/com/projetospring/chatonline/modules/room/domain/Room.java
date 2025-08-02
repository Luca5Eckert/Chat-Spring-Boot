package com.projetospring.chatonline.modules.room.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.projetospring.chatonline.modules.room.domain.enums.TypeRoom;
import com.projetospring.chatonline.modules.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Room {
    private final static int MAX_PEOPLE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;

    @Column(nullable = false)
    private String nameRoom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final TypeRoom type;

    @Column(nullable = false)
    private int numberOfPeople;

    @Column(nullable = false)
    private final LocalDateTime createAt;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "create_by", nullable = false)
    private final User createBy;

    private Room(UUID id, String nameRoom, TypeRoom type, int numberOfPeople, LocalDateTime createAt, String description, User createBy) {
        this.id = id;
        this.nameRoom = nameRoom;
        this.type = type;
        this.numberOfPeople = numberOfPeople;
        this.createAt = createAt;
        this.description = description;
        this.createBy = createBy;
    }

    public static Room createRoom(UUID id, String nameRoom, TypeRoom type, int numberOfPeople, LocalDateTime createAt, String description, User createBy) {
        return new Room(id, nameRoom, type, numberOfPeople, createAt, description, createBy);
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (!(numberOfPeople >= 0)) {
            this.numberOfPeople = numberOfPeople;
        }
        throw new RuntimeException("The number of people in a room can not be negative");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String getDescription() {
        return new String(description);
    }

    boolean isItFull() {
        return numberOfPeople == MAX_PEOPLE;
    }

    static int getMaxPeople() {
        return MAX_PEOPLE;
    }

    UUID getId() {
        return id;
    }

    TypeRoom getType() {
        return type;
    }

    int getNumberOfPeople() {
        return numberOfPeople;
    }

    LocalDateTime getCreateAt() {
        return createAt;
    }

}

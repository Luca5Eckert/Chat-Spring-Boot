package com.projetospring.chatonline.infrastructure;

import com.projetospring.chatonline.modules.message.aplication.repository.JpaMessageRepository;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepository;
import com.projetospring.chatonline.modules.message.aplication.repository.MessageRepositoryImpl;
import com.projetospring.chatonline.modules.room.aplication.repository.JpaRoomRepository;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepositoryImpl;
import com.projetospring.chatonline.modules.user.aplication.repository.JpaUserRepository;
import com.projetospring.chatonline.modules.user.aplication.repository.UserRepository;
import com.projetospring.chatonline.modules.user.aplication.repository.UserRepositoryImpl;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.JpaUserStatusRoomRepository;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.UserStatusRoomRepository;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.UserStatusRoomRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserRepositoryImpl(jpaUserRepository);
    }

    @Bean
    public RoomRepository roomRepository(JpaRoomRepository jpaRoomRepository) {
        return new RoomRepositoryImpl(jpaRoomRepository);
    }

    @Bean
    public MessageRepository messageRepository(JpaMessageRepository jpaMessageRepository) {
        return new MessageRepositoryImpl(jpaMessageRepository);
    }

    @Bean
    public UserStatusRoomRepository userStatusRoomRepository(JpaUserStatusRoomRepository jpaUserStatusRoomRepository) {
        return new UserStatusRoomRepositoryImpl(jpaUserStatusRoomRepository);
    }
}

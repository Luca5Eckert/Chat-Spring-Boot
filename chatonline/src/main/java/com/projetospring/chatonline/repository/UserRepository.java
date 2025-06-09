package com.projetospring.chatonline.repository;

import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.projetospring.chatonline.model.User;

public class UserRepository implements Repository<UUID, User> {

}

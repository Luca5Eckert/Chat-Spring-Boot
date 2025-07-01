package com.projetospring.chatonline.controller;

import com.projetospring.chatonline.dtos.EditUserStatusRoomDto;
import com.projetospring.chatonline.dtos.ResponseDto;
import com.projetospring.chatonline.service.cases.EditUserStatusRoomCase;
import com.projetospring.chatonline.service.cases.EnterRoomCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStatusRoomController {

    private final EditUserStatusRoomCase editUserStatusRoomCase;

    public UserStatusRoomController(EditUserStatusRoomCase editUserStatusRoomCase) {
        this.editUserStatusRoomCase = editUserStatusRoomCase;
    }

    @PostMapping("/api/userstatusroom/edit")
    public ResponseEntity<ResponseDto> editUserStatusRoom(@Valid @RequestBody EditUserStatusRoomDto editUserStatusRoomDto){
        return ResponseEntity.ok(new ResponseDto(202, "User status in room changed successfully", null));
    }


    public ResponseEntity<ResponseDto> addUserInRoom(@Valid @RequestBody EnterRoomCase enterInTheRoomCase){
        return null;
    }

}

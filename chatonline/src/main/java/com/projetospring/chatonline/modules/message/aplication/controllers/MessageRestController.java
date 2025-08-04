package com.projetospring.chatonline.modules.message.aplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.modules.message.aplication.dtos.SendMenssageDto;
import com.projetospring.chatonline.modules.user.domain.User;
import com.projetospring.chatonline.modules.message.domain.cases.SendMessageCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/message")
public class MessageRestController {


}

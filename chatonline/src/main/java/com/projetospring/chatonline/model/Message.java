package com.projetospring.chatonline.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_tb")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	private final User sendBy;
	private final Room sendFor;
	private LocalDateTime sendAt;

	private final String content;

	public Message(UUID id, User sendBy, Room sendFor, LocalDateTime sendAt, String content) {
		this.id = id;
		this.sendBy = sendBy;
		this.sendFor = sendFor;
		this.sendAt = sendAt;
		this.content = content;
	}

	public UUID getId() {
		return id;
	}

	public User getSendBy() {
		return sendBy;
	}

	public Room getSendFor() {
		return sendFor;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

}

package com.projetospring.chatonline.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_tb")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private final User sendBy;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private final Room sendFor;

	@Column(nullable = false)
	private final LocalDateTime sendAt;

	@Column(nullable = false)
	private String content;
	
	
	public Message() {
		this.id = null;
		this.sendBy = null;
		this.sendFor = null;
		this.sendAt = null;
	}

	private Message(UUID id, User sendBy, Room sendFor, LocalDateTime sendAt, String content) {
		this.id = id;
		this.sendBy = sendBy;
		this.sendFor = sendFor;
		this.sendAt = sendAt;
		this.content = content;
	}

	public static Message createMessage(UUID id, User sendBy, Room sendFor, LocalDateTime sendAt, String content) {
		return new Message(id, sendBy, sendFor, sendAt, content);
	}

	UUID getId() {
		return id;
	}

	User getSendBy() {
		return sendBy;
	}

	Room getSendFor() {
		return sendFor;
	}

	LocalDateTime getSendAt() {
		return sendAt;
	}

	String getContent() {
		return content;
	}

}
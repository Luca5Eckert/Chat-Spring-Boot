package com.projetospring.chatonline.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.Table;

@Entity
@Table(name = "message_tb")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 

	@ManyToOne 
	@JoinColumn(name = "sender_id", nullable = false) 
	private User sendBy; 

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room sendFor; 

	@Column(nullable = false)
	private LocalDateTime sendAt;

	@Column(nullable = false)
	private String content; 

	public Message() {
	}

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

	public void setId(UUID id) {
		this.id = id;
	}

	public User getSendBy() {
		return sendBy;
	}

	public void setSendBy(User sendBy) {
		this.sendBy = sendBy;
	}

	public Room getSendFor() {
		return sendFor;
	}

	public void setSendFor(Room sendFor) {
		this.sendFor = sendFor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}
}
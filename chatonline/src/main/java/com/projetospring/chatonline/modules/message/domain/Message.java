package com.projetospring.chatonline.modules.message.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id;

	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private final UserEntity sendBy;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private final Room sendFor;

	@Column(nullable = false)
	private final LocalDateTime sendAt;

	@Column(nullable = false)
	private String content;

	@Version
	private long version;

	private Message(UUID id, UserEntity sendBy, Room sendFor, LocalDateTime sendAt, String content) {
		this.id = id;
		this.sendBy = sendBy;
		this.sendFor = sendFor;
		this.sendAt = sendAt;
		this.content = content;
	}

	public static Message createMessage(UUID id, UserEntity sendBy, Room sendFor, LocalDateTime sendAt, String content) {
		return new Message(id, sendBy, sendFor, sendAt, content);
	}

	UUID getId() {
		return id;
	}

	UserEntity getSendBy() {
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
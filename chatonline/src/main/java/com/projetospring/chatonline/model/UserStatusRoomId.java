package com.projetospring.chatonline.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UserStatusRoomId {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	public UserStatusRoomId(User user, Room room) {
		this.user = user;
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public Room getRoom() {
		return room;
	}

}

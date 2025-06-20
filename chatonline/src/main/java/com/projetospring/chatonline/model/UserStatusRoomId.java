package com.projetospring.chatonline.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class UserStatusRoomId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	public UserStatusRoomId() {
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserStatusRoomId)) return false;
		UserStatusRoomId that = (UserStatusRoomId) o;
		return user.equals(that.user) && room.equals(that.room);
	}

	@Override
	public int hashCode() {
		return user.hashCode() + room.hashCode();
	}
}

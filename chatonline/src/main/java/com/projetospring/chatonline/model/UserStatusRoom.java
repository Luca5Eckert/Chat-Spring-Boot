package com.projetospring.chatonline.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class UserStatusRoom {

	@Column(nullable = false)
	private final User user;

	@Column(nullable = false)
	private final Room room;

	@Column(nullable = false)
	private TypeRoomAccess roomAccess;

	private UserStatusRoom(@NotNull UserStatusRoomBuilder userStatusRoomBuilder) {
		this.user = userStatusRoomBuilder.user;
		this.room = userStatusRoomBuilder.room;
		this.roomAccess = userStatusRoomBuilder.roomAccess;
	}

	public User getUser() {
		return user;
	}

	public Room getRoom() {
		return room;
	}

	public TypeRoomAccess getRoomAccess() {
		return roomAccess;
	}

	public void setRoomAccess(@NotNull TypeRoomAccess roomAccess) {
		this.roomAccess = roomAccess;
	}

	public class UserStatusRoomBuilder {

		private final User user;
		private final Room room;

		private TypeRoomAccess roomAccess;

		public UserStatusRoomBuilder(User user, Room room) {
			this.user = user;
			this.room = room;
		}

		public void setRoomAcesss(TypeRoomAccess roomAccess) {
			this.roomAccess = roomAccess;
		}

		public UserStatusRoom builder() {
			return new UserStatusRoom(this);
		}

	}
}

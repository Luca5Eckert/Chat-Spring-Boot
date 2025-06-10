package com.projetospring.chatonline.model;

public class UserStatusRoom {

	private final User user;
	private final Room room;
	private final TypeRoomAccess roomAccess;

	private UserStatusRoom(UserStatusRoomBuilder userStatusRoomBuilder) {
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

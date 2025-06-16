package com.projetospring.chatonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Table(name = "user_status_room_tb")
@Value
@NoArgsConstructor
public class UserStatusRoom {

	@EmbeddedId
	private final UserStatusRoomId id;

	@Column(nullable = false)
	private TypeRoomAccess roomAccess;

	private UserStatusRoom(@NotNull UserStatusRoomBuilder userStatusRoomBuilder) {
		this.id = userStatusRoomBuilder.id;
		this.roomAccess = userStatusRoomBuilder.roomAccess;
	}

	public User getUser() {
		return id.getUser();
	}

	public Room getRoom() {
		return id.getRoom();
	}

	public TypeRoomAccess getRoomAccess() {
		return roomAccess;
	}

	public void setRoomAccess(@NotNull TypeRoomAccess roomAccess) {
		this.roomAccess = roomAccess;
	}

	public class UserStatusRoomBuilder {

		private final UserStatusRoomId id;

		private TypeRoomAccess roomAccess;

		public UserStatusRoomBuilder(User user, Room room) {
			this.id = new UserStatusRoomId(user, room);
		}

		public void setRoomAcesss(TypeRoomAccess roomAccess) {
			this.roomAccess = roomAccess;
		}

		public UserStatusRoom builder() {
			return new UserStatusRoom(this);
		}

	}
}

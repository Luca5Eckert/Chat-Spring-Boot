package com.projetospring.chatonline.modules.userstatusroom.domain;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.TypeRoomAccess;
import com.projetospring.chatonline.modules.room.domain.Room;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_status_room_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserStatusRoom {

	@EmbeddedId
	private final UserStatusRoomId id;

	@Column(nullable = false)
	private TypeRoomAccess roomAccess;

	@Column(nullable = false)
	private boolean active;

	private UserStatusRoom(@NotNull UserStatusRoomBuilder userStatusRoomBuilder) {
		this.id = userStatusRoomBuilder.id;
		this.roomAccess = userStatusRoomBuilder.roomAccess;
		this.active = userStatusRoomBuilder.active;
	}


	public boolean canSendMessage() {
		return switch (roomAccess) {
			case BLOCKED -> false;
			default -> true;
		};
	}

	public boolean canEnterRoom() {
		return switch(roomAccess){
			case BLOCKED -> false;
			default -> true;
		};
	}

	public boolean canDeleteRoom() {
		return switch(roomAccess){
			case ADMINISTRATOR -> true;
			default -> false;
		};
	}


	public UserEntity getUser() {
		return id.getUser();
	}

	public Room getRoom() {
		return id.getRoom();
	}

    public void setRoomAccess(@NotNull TypeRoomAccess roomAccess) {
		this.roomAccess = roomAccess;
	}

	public void setActive(boolean active){
		this.active = active;
	}

    public boolean canEditRoom() {
		return switch(roomAccess){
			case ADMINISTRATOR -> true;
			default -> false;
		};
	}

	public boolean haveAdministratorPermission() {
		return roomAccess.equals(TypeRoomAccess.ADMINISTRATOR);
	}

	public static class UserStatusRoomBuilder {

		private final UserStatusRoomId id;

		private TypeRoomAccess roomAccess;

		private boolean active;

		public UserStatusRoomBuilder(UserEntity user, Room room) {
			this.id = new UserStatusRoomId(user, room);
		}

		public UserStatusRoomBuilder(UserStatusRoomId userStatusRoomId) {
			this.id = userStatusRoomId;
		}

		public UserStatusRoomBuilder setRoomAccess(TypeRoomAccess roomAccess) {
			this.roomAccess = roomAccess;
			return this;
		}

		public UserStatusRoomBuilder setActive(boolean active){
			this.active = active;
			return this;
		}

		public UserStatusRoom build() {
			return new UserStatusRoom(this);
		}

	}
}

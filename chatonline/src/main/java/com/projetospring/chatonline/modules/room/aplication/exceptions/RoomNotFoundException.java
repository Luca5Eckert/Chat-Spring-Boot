package com.projetospring.chatonline.modules.room.aplication.exceptions;

import com.projetospring.chatonline.core.exceptions.DatabaseEntityNotFoundException;

public class RoomNotFoundException extends DatabaseEntityNotFoundException {
  public RoomNotFoundException(String message) {
    super(message);
  }
}

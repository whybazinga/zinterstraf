package com.vvopaa.zinterstraf.exception;

public class UsernameAlreadyExistsException extends Exception {

  public UsernameAlreadyExistsException(String name, Throwable cause) {
    super("Username with username " + name + " already exists.", cause);
  }
}

package com.vvopaa.zinterstraf.model.enums;

public enum UserStatuses {
    DISABLED(0),
    ENABLED(1),
    LOCKED(2),
    EXPIRED(3);

    private final int status;

    private UserStatuses(int status) {
        this.status = status;
    }

    public int getValue() {
        return status;
    }

}

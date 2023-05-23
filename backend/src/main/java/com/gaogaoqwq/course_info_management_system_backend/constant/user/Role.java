package com.gaogaoqwq.course_info_management_system_backend.constant.user;

public enum Role {
    ADMIN("ROLE_ADMIN"),

    TEACHER("ROLE_TEACHER"),
    STUDENT("ROLE_STUDENT"),

    GUEST("ROLE_GUEST");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

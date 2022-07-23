package com.io.itsduser.model.types;

public enum UserRole {

    SUPER_ADMIN(0, "super_admin"),
    ADMIN(1,"admin"),
    USER(2,"user"),
    OWNER(3, "owner"),
    RESPONDER(4, "responder");

    private Integer id;
    private String roleName;

    UserRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}

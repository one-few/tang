package net.ttcxy.tangtang.entity;

import lombok.Data;

@Data
public class UserRole {

    static final long serialVersionUID = 1L;

    private String userId;

    private String roleId;
}
package com.blps.CommentService.Entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class Account implements Serializable {
    private Long id;
    private String firstName;

    private String lastName;

    private String username;
    private String password;

    private Role role;

    public Account(Long id, String firstName, String lastName, String username, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }


}

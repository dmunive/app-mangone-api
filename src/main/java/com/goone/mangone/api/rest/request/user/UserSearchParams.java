package com.goone.mangone.api.rest.request.user;

import lombok.Data;

@Data
public class UserSearchParams {
    private String name;
    private String lastName;
    private String email;
    private Short status;
}

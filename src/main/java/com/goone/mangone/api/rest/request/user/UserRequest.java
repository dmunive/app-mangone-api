package com.goone.mangone.api.rest.request.user;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    public interface UserCreateRequest {
    }
    public interface UserUpdateRequest {
    }
    @NotEmpty(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    @NotNull(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    private String name;
    @NotEmpty(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    @NotNull(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    private String lastName;
    @Email(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    @NotEmpty(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    @NotNull(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    private String email;
    @NotEmpty(groups = { UserCreateRequest.class })
    @NotNull(groups = { UserCreateRequest.class })
    private String password;
    @Digits(integer=1, fraction=0, groups = { UserCreateRequest.class, UserUpdateRequest.class })
    @NotNull(groups = { UserCreateRequest.class, UserUpdateRequest.class })
    private Short status;
}

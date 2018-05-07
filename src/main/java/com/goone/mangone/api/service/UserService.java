package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.UserEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.user.UserRequest;
import com.goone.mangone.api.rest.request.user.UserSearchParams;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserEntity createUser(UserRequest userRequest);
    UserEntity readUser(Long userId);
    UserEntity updateUser(Long userId, UserRequest userRequest);
    Paginator<UserEntity> searchUser(UserSearchParams userSearchParams, Pageable pageable);
    UserEntity deleteUser(Long userId);
}

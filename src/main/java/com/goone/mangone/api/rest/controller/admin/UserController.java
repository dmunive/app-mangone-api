package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.entity.UserEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.user.UserRequest;
import com.goone.mangone.api.rest.request.user.UserSearchParams;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.UserService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AdminController{

    @Autowired
    ManageMessageApplication manageMessageApplication;
    
    @Autowired
    UserService userService;

    @PostMapping(path = "/users")
    public ResponseEntity<?> createUser(@Validated({ UserRequest.UserCreateRequest.class }) @RequestBody UserRequest userRequest) {
        UserEntity user = userService.createUser(userRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_USERS_CREATE), user);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<?> readUser(@PathVariable Long userId){
        UserEntity user = userService.readUser(userId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_USERS_READ), user);
        return ResponseEntity.ok().body(genericResponse);
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Validated({ UserRequest.UserUpdateRequest.class }) @RequestBody UserRequest userRequest) {
        UserEntity user = userService.updateUser(userId,userRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_USERS_UPDATE), user);
        return ResponseEntity.ok().body(genericResponse);
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<?>  deleteUser(@PathVariable Long userId){
        UserEntity user = userService.deleteUser(userId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_USERS_DELETE), user);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> searchUser(@ModelAttribute UserSearchParams userSearchParams, Pageable pageable){
        Paginator<UserEntity> users = userService.searchUser(userSearchParams, pageable);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_USERS_SEARCH), users);
        return ResponseEntity.ok().body(genericResponse);
    }

}

package com.goone.mangone.api.service.impl;

import com.goone.mangone.api.entity.UserEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.dao.UserDao;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.exception.UniqueFieldException;
import com.goone.mangone.api.rest.request.user.UserRequest;
import com.goone.mangone.api.rest.request.user.UserSearchParams;
import com.goone.mangone.api.service.UserService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity createUser(UserRequest userRequest) {
        Optional<UserEntity> userExist = userDao.findByEmail(userRequest.getEmail());
        if (userExist.isPresent()) {
            throw new UniqueFieldException("email", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_EMAIL_UNIQUE));
        }
        UserEntity user = new UserEntity();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedAt(new Date());
        user.setStatus(userRequest.getStatus());
        user = userDao.save(user);
        return user;
    }

    public UserEntity readUser(Long userId) {
        Optional<UserEntity> user = userDao.findById(userId);
        if(!user.isPresent()){
            throw new NotExistEntityException("userId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_ID_NOT_EXIST));
        }
        return user.get();
    }

    @Transactional
    public UserEntity updateUser(Long userId, UserRequest userRequest) {
        Optional<UserEntity> userExist = userDao.findById(userId);
        if(!userExist.isPresent()) {
            throw new NotExistEntityException("userId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_ID_NOT_EXIST));
        }
        Optional<UserEntity> userEmailExist = userDao.findByUserIdNotAndEmail(userId, userRequest.getEmail());
        if(userEmailExist.isPresent()) {
            throw new UniqueFieldException("email", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_EMAIL_UNIQUE));
        }
        UserEntity user = userExist.get();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUpdatedAt(new Date());
        user.setStatus(userRequest.getStatus());
        user = userDao.save(user);
        return user;
    }

    public Paginator<UserEntity> searchUser(UserSearchParams params, Pageable pageable){
        Page<UserEntity> pageUserEntity= userDao.searchUser(params.getName(), params.getLastName(), params.getEmail(), params.getStatus(), pageable);
        return new Paginator<>(pageUserEntity);
    }

    @Transactional
    public UserEntity deleteUser(Long userId) {
        Optional<UserEntity> userExist = userDao.findById(userId);
        if(!userExist.isPresent()){
            throw new NotExistEntityException("userId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_ID_NOT_EXIST));
        }
        UserEntity user = userExist.get();
        user.setDeletedAt(new Date());
        user = userDao.save(user);
        return user;
    }

}

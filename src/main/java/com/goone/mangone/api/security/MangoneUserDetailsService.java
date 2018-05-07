package com.goone.mangone.api.security;

import com.goone.mangone.api.dao.UserDao;
import com.goone.mangone.api.entity.UserEntity;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MangoneUserDetailsService implements UserDetailsService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userDao.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("")
        );
        return MangoneUser.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        UserEntity user = userDao.findById(id).orElseThrow(
                () -> new NotExistEntityException("userId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_USERS_ID_NOT_EXIST))
        );
        return MangoneUser.create(user);
    }

}

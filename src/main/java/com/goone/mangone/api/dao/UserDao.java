package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u  " +
           "WHERE (:name IS NULL OR u.name LIKE CONCAT('%',:name,'%')) " +
           "AND (:lastName IS NULL OR u.lastName LIKE CONCAT('%',:lastName,'%')) " +
           "AND (:email IS NULL OR u.email LIKE CONCAT('%',:email,'%')) " +
           "AND (:status IS NULL OR u.status = :status) AND u.deletedAt IS NULL")
    Page<UserEntity> searchUser(@Param("name") String name, @Param("lastName") String lastName, @Param("email") String email, @Param("status") Short status, Pageable pageable);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserIdNotAndEmail(Long userId, String email);
}

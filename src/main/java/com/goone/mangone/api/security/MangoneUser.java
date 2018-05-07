package com.goone.mangone.api.security;

import com.goone.mangone.api.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class MangoneUser implements UserDetails {

    private Long userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    MangoneUser(Long userId, String name, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MangoneUser create(UserEntity user) {
        GrantedAuthority authority =  new SimpleGrantedAuthority("ROLE_ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new MangoneUser(
                user.getUserId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}

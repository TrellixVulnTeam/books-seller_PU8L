package com.bsoft.springbootbookseller.security;

import com.bsoft.springbootbookseller.model.Role;
import com.bsoft.springbootbookseller.model.User;
import com.bsoft.springbootbookseller.util.SecurityUtils;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    transient private String password; //dont show up on serialized places
    transient private User user; //user for only login operations, dont use in JWT
    private Set<GrantedAuthority> authorities;


    public static UserPrincipal createSuperUser(){
        Set<GrantedAuthority> authorities =  Set.of(SecurityUtils.convertToAuthority(Role.SYSTEM_MANAGER.name()));

        return UserPrincipal.builder()
                .id(-1L)
                .username("system-administrator")
                .authorities(authorities)
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}

package com.linesbymartin.securitypractice.user.domain;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.linesbymartin.securitypractice.common.domain.BaseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(nullable = false, length = 254, unique = true) // the max length of an email is 254
    private String email;

    @Column(nullable = false, length = 72) // bcrypt length
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}

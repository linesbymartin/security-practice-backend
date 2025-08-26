package com.linesbymartin.securitypractice.user.domain;

import com.linesbymartin.securitypractice.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 254, unique = true) // the max length of an email is 254
    private String email;

    @Column(nullable = false, length = 72) // bcrypt length
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;
}

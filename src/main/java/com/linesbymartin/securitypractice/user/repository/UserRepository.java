package com.linesbymartin.securitypractice.user.repository;

import com.linesbymartin.securitypractice.common.repository.BaseRepository;
import com.linesbymartin.securitypractice.user.domain.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}

package com.dongjae.nearChatProejct.User.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByDeviceId(String deviceId);

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByDeviceIdAndName(String deviceId, String name);
}

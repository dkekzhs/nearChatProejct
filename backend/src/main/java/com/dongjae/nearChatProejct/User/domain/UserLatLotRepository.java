package com.dongjae.nearChatProejct.User.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLatLotRepository extends JpaRepository<UserLatLotEntity, Long> {
    Optional<UserLatLotEntity> findByUserEntity_Id(Long userId);
}

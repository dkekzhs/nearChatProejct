package com.dongjae.nearChatProejct.User.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String deviceId;

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    @OneToOne(mappedBy = "userEntity")
    private  UserLatLotEntity userLatLotEntity;
    @Builder
    public UserEntity(String deviceId, String name,UserRole role,UserLatLotEntity userLatLotEntity) {
        this.deviceId = deviceId;
        this.name = name;
        this.role = role;
        this.userLatLotEntity = userLatLotEntity;
    }
}

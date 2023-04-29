package com.dongjae.nearChatProejct.User.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserLatLotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private Double lat;
    @Column(nullable = false)
    private Double lot;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @Column(nullable = false)
    private int radius;

    public void UpdateCoordinate(Double lat, Double lot){
        this.lat = lat;
        this.lot = lot;
    }

    public void UpdateRadius(int radius){
        this.radius = radius;
    }

    @Builder
    public UserLatLotEntity(int radius, Double lat, Double lot,UserEntity userEntity) {
        this.userEntity = userEntity;
        this.radius = radius;
        this.lat = lat;
        this.lot = lot;
    }


}

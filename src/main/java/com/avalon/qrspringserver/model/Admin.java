package com.avalon.qrspringserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "AdminModel")
public class Admin extends UserModel {
    @OneToOne
    @JoinColumn(name = "REST_PK", nullable = true)
    private Restaurant restaurant;

    private boolean _admin = true;
}

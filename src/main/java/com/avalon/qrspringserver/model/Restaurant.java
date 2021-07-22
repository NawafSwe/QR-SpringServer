package com.avalon.qrspringserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Restaurant")
public class Restaurant {
    private
    @GeneratedValue
    @Id
    String id;
    private String name;
    @OneToMany
    @JoinColumn(name = "PK_Menu", nullable = false)
    private List<Menu> menus;
    @Column(unique = true)
    private String url;
    @Column(unique = true)
    private String qr;
    @Column(unique = true)
    private String email;

}

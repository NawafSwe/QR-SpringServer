package com.avalon.qrspringserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Restaurant")
public class Restaurant {
    private
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Id
    String id;
    private String name;
    @OneToMany
    @JoinColumn(name = "PK_Menu", nullable = true)
    private List<Menu> menus;
    @Column(unique = true, nullable = true)
    private String url;
    @Column(unique = true, nullable = true)
    private String qr;
    @Column(unique = true, nullable = false)
    private String email;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}

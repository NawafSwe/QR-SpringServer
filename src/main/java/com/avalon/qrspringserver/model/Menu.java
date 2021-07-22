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
@Table(name = "Menu")
public class Menu {
    private @Id
    @GeneratedValue
    String id;

    private String name;
    // of type image maybe
    private String logo;
    private String description;

    @OneToMany
    @JoinColumn(name = "PK_Category", nullable = false)
    private List<Category> categories;

    // images maybe
}

package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Restaurant")
public class Restaurant {
    private
    @GeneratedValue
    @Id
    String id;


    private String name;
    // db ref
    //private List<Menu> menus;
    @Column(unique = true)
    private String url;
    @Column(unique = true)
    private String qr;
    @Column(unique = true)
    private String email;

}

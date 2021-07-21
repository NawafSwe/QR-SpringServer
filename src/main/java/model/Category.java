package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    private @Id
    @GeneratedValue
    String id;

    @OneToMany
    @JoinColumn(name = "FK_Item", nullable = false)
    private List<Item> items;
}

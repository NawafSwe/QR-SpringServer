package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Item")
public class Item {
    private
    @GeneratedValue
    @Id
    String id;
    private String description;
    private Float price;
    // cannot set array of thing, it must be extracted to another class
    // private String[] images;
}

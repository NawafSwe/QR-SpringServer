package com.avalon.qrspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
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
@Table(name = "Category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "FK_Item", nullable = true)
    private List<Item> items;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonIgnore
    private Date createdAt;

    @UpdateTimestamp
    @JsonIgnore
    private Date updatedAt;
}

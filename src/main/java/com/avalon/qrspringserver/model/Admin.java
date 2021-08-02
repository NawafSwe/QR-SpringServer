package com.avalon.qrspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin extends UserModel {
}
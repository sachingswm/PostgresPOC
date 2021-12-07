package com.example.vgproject2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Data
@Table(name="customers")
public class Customers {
    @Id
    private int id;
    private String username;
    private String email;
    private String phone;
    private String address;
}

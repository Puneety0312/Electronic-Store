package com.electronic.store.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    String id;
    String name;
    @ManyToMany
    List<User> users;
}

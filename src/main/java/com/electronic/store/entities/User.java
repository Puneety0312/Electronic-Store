package com.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class User {

    @Id
    private int id;
    private String name;
    @Column( unique = true)
    private String email;
    private String password;
    @Column(length = 10)
    private long phone;
    private String imageName;
}

package com.electronic.store.entities;

import jakarta.persistence.*;
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

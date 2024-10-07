package com.electronic.store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto
{
    private int id;
    private String name;
    private String email;
    private String password;
    private long phone;
    private String imageName;
}

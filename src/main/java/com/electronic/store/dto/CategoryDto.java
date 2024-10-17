package com.electronic.store.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private int id;
    @NotBlank
    @Min(value = 4)
    private String title;
    private String description;
    private String coverImage;
}

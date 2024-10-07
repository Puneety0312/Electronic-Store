package com.electronic.store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiMessage {
    private String message;
    private boolean success;
    private HttpStatus status;

}

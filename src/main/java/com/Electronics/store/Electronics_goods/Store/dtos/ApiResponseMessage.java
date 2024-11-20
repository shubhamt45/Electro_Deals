package com.Electronics.store.Electronics_goods.Store.dtos;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus status;
}

package com.grundfos.athariflowbox.iotservice.common;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {
    private boolean isSuccess = true;
    private String message;
    private T data;
}

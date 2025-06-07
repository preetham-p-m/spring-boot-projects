package com.pmp.dev_lab.order.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericError {
    private int code;
    private String message;
}

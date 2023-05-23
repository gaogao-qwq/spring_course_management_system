package com.gaogaoqwq.course_info_management_system_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ParamException extends RuntimeException {
    private String message;
    private Integer code;
}

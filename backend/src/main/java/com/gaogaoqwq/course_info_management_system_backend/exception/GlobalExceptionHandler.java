/*
 * A RestFUL Web API
 * Copyright (C) 2023. Zhihao Zhou<gaogaoqwq@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gaogaoqwq.course_info_management_system_backend.exception;

import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(@NotNull Exception e) {
        e.printStackTrace();
        return R.setResult(ResultCode.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public R error(@NotNull NullPointerException e) {
        e.printStackTrace();
        return R.setResult(ResultCode.NULL_POINTER_EXCEPTION);
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R HttpRequestMethodNotSupportedExceptionHandler(@NotNull HttpRequestMethodNotSupportedException e) {
        return R.failure()
                .code(ResultCode.METHOD_NOT_ALLOWED.getCode())
                .message(ResultCode.METHOD_NOT_ALLOWED.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public R AuthenticationExceptionHandler(@NotNull AuthenticationException e) {
        return R.failure()
                .code(ResultCode.UNAUTHORIZED.getCode())
                .message(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ParamException.class)
    public R ParamExceptionHandler(@NotNull ParamException e) {
        return R.failure()
                .code(e.getCode())
                .message(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(QueryException.class)
    public R QueryExceptionHandler(@NotNull QueryException e) {
        return R.failure()
                .code(ResultCode.NOT_FOUND.getCode())
                .message(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public R SQLExceptionHandler(@NotNull SQLException e) {
        return R.failure()
                .code(ResultCode.CONFLICT.getCode())
                .message(e.getMessage());
    }

}

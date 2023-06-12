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

package com.gaogaoqwq.course_info_management_system_backend.response;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Long timestamp;

    private Object data;

    private R setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    private R setMessage(String message) {
        this.message = message;
        return this;
    }

    private R setData(Object obj) {
        this.data = obj;
        return this;
    }

    private R setCode(Integer code) {
        this.code = code;
        return this;
    }

    private R(Long timestamp) {
        this.timestamp = timestamp;
    }


    public static R success() {
        return new R(System.currentTimeMillis())
                .setSuccess(ResultCode.SUCCESS.isSuccess())
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(ResultCode.SUCCESS.getMessage());
    }

    public static R failure() {
        return new R(System.currentTimeMillis())
                .setSuccess(ResultCode.FAIL.isSuccess())
                .setCode(ResultCode.FAIL.getCode())
                .setMessage(ResultCode.FAIL.getMessage());
    }

    public static R setResult(@NotNull ResultCode result) {
        return new R(System.currentTimeMillis())
                .setSuccess(result.isSuccess())
                .setCode(result.getCode())
                .setMessage(result.getMessage());
    }

    // 自定义返回数据
    public R data(Object obj) {
        return this.setData(obj);
    }

    // 自定义状态信息
    public R message(String message) {
        return this.setMessage(message);
    }

    // 自定义状态码
    public R code(Integer code) {
        return this.setCode(code);
    }

    // 自定义返回结果
    public R success(Boolean success) {
        return this.setSuccess(success);
    }

}

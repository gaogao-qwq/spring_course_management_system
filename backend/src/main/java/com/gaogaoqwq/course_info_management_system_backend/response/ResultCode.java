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

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {
    SUCCESS(true, HttpStatus.OK.value(), "成功"),

    FAIL(false, HttpStatus.BAD_REQUEST.value(), "请求失败"),
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED.value(), "未认证或签名错误"),
    FORBIDDEN(false, HttpStatus.FORBIDDEN.value(), "资源拒绝访问"),
    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "接口不存在"),
    METHOD_NOT_ALLOWED(false, HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法错误"),
    CONFLICT(false, HttpStatus.CONFLICT.value(), "与数据库中已有数据冲突"),

    INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "内部服务器错误"),

    NULL_POINTER_EXCEPTION(false, 200001, "空指针异常"),
    PARAM_ERROR(false, 200002, "查询参数错误"),
    PARAM_CONFLICT(false, 200003, "查询参数冲突");


    private final boolean success;

    private final Integer code;

    private final String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}

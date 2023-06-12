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

package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user;

import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;


public interface UserService {
    Map<Object, Object> getUserInfo(@NotNull UserDetails userDetails);

    long getUserCount();

    List<Map<Object, Object>> getUserInfoByPage(@NotNull Integer size, @NotNull Integer page);

    User updateUserInfo(@NotNull Map<String, String> request);
}

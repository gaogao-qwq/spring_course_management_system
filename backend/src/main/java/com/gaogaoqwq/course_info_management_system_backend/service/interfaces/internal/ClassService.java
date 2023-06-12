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

package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClassService {

    Class save(@NotNull Class cls, @NotNull Major major) throws SQLException, ParamException;

    long getClassCount();

    List<Class> findClassList();

    Page<Class> getClassByPage(@NotNull Integer page, @NotNull Integer size);

    List<Class> findClassesByMajorId(@NotNull Long id);

    List<Class> findClassesByMajorName(@NotNull String name);

    Optional<Class> findClassById(@NotNull Long id);

    Optional<Class> findClassByName(@NotNull String name);

    Optional<Class> findClassByCodeName(@NotNull String codeName);

    Class updateClass(@NotNull Class cls) throws QueryException;

    void delete(@NotNull Long id) throws QueryException;

}

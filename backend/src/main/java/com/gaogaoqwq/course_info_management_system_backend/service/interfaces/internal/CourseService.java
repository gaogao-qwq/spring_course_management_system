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
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course save(@NotNull Course course, @NotNull Teacher teacher, @NotNull Class cls)
            throws SQLException, ParamException;

    long getCourseCount();

    List<Course> findCourseList();

    Page<Course> getCourseByPage(@NotNull Integer page, @NotNull Integer size);

    List<Course> findCoursesByName(@NotNull String name);

    List<Course> findCoursesByTeacherId(@NotNull Long id);

    List<Course> findCoursesByTeacherName(@NotNull String name);

    List<Course> findCoursesByClassId(@NotNull Long id);

    List<Course> findCoursesByClassName(@NotNull String name);

    Optional<Course> findCourseById(@NotNull Long id);

    Optional<Course> findCourseByCodeName(@NotNull String codeName);

    Course update(@NotNull Course course) throws QueryException;

    void delete(@NotNull Long id) throws QueryException;
}

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

package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCodeName(@NotNull String codeName);
    List<Course> findCoursesByName(@NotNull String name);
    List<Course> findCoursesByFkTeacher_Id(@NotNull Long id);
    List<Course> findCoursesByFkTeacher_Name(@NotNull String name);
    List<Course> findCoursesByFkClass_Id(@NotNull Long id);
    List<Course> findCoursesByFkClass_Name(@NotNull String name);
}

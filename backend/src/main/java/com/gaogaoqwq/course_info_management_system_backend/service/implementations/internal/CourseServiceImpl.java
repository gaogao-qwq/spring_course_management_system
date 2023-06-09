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

package com.gaogaoqwq.course_info_management_system_backend.service.implementations.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.ClassRepository;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.CourseRepository;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.TeacherRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.CourseService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;

    @Override
    public Course save(@NotNull Course course, @NotNull Teacher teacher, @NotNull Class cls)
            throws SQLException, ParamException {
        if (courseRepository.existsById(course.getId())) {
            throw new SQLException("课程 id 冲突");
        }
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacher.getId());
        Optional<Class> classOptional = classRepository.findById(cls.getId());
        if (teacherOptional.isEmpty()) {
            throw new QueryException("未找到对应教师");
        }
        if (classOptional.isEmpty()) {
            throw new QueryException("未找到对应班级");
        }
        course.setFkTeacher(teacherOptional.get());
        course.setFkClass(classOptional.get());
        return courseRepository.save(course);
    }

    @Override
    public long getCourseCount() {
        return courseRepository.count();
    }

    @Override
    public List<Course> findCourseList() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> getCourseByPage(@NotNull Integer page, @NotNull Integer size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Course> findCoursesByName(@NotNull String name) {
        return courseRepository.findCoursesByName(name);
    }

    @Override
    public List<Course> findCoursesByTeacherId(@NotNull Long id) {
        return courseRepository.findCoursesByFkTeacher_Id(id);
    }

    @Override
    public List<Course> findCoursesByTeacherName(@NotNull String name) {
        return courseRepository.findCoursesByFkTeacher_Name(name);
    }

    @Override
    public List<Course> findCoursesByClassId(@NotNull Long id) {
        return courseRepository.findCoursesByFkTeacher_Id(id);
    }

    @Override
    public List<Course> findCoursesByClassName(@NotNull String name) {
        return courseRepository.findCoursesByFkTeacher_Name(name);
    }

    @Override
    public Optional<Course> findCourseById(@NotNull Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findCourseByCodeName(@NotNull String codeName) {
        return courseRepository.findCourseByCodeName(codeName);
    }

    @Override
    public Course update(@NotNull Course course) throws QueryException {
        if (!courseRepository.existsById(course.getId())) {
            throw new QueryException("未找到对应的课程");
        }
        return courseRepository.save(course);
    }

    @Override
    public void delete(@NotNull Long id) throws QueryException {
        Optional<Course> opt = courseRepository.findById(id);
        if (opt.isEmpty()) {
            throw new QueryException("未找到对应的课程");
        }
        courseRepository.delete(opt.get());
    }
}

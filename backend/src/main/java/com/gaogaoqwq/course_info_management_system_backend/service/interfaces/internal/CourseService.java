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

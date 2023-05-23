package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCodeName(@NotNull String codeName);
    List<Course> findCoursesByName(@NotNull String name);
    List<Course> findCoursesByFkTeacher_Id(@NotNull Long id);
    List<Course> findCoursesByFkTeacher_Name(@NotNull String name);
    List<Course> findCoursesByFkClass_Id(@NotNull Long id);
    List<Course> findCoursesByFkClass_Name(@NotNull String name);
}

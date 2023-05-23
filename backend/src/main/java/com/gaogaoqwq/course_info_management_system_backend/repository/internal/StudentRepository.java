package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByFkClass_Name(@NotNull String name);

    List<Student> findStudentsByFkClass_Id(@NotNull Long id);

    List<Student> findStudentsByFkClass_FkMajor_Id(@NotNull Long id);

    List<Student> findStudentsByFkClass_FkMajor_Name(@NotNull String name);

    List<Student> findStudentsByName(@NotNull String name);

    List<Student> findStudentsByGender(@NotNull Short gender);

    List<Student> findStudentsByFkClass_FkCourse_Id(@NotNull Long studentId);

    Optional<Student> findStudentByCodeName(@NotNull String codeName);

}

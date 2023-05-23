package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByCodeName(@NotNull String codeName);
    Optional<Teacher> findTeacherByCodeName(@NotNull String codeName);
    List<Teacher> findTeachersByName(@NotNull String name);
    List<Teacher> findTeachersByGenderOrderByGenderAsc(@NotNull Short gender);
}

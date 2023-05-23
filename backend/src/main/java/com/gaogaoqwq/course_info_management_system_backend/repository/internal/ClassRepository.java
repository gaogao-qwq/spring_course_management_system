package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
    boolean existsByName(@NotNull String name);
    boolean existsByCodeName(@NotNull String codeName);
    List<Class> findClassesByFkMajor_Id(@NotNull Long id);
    List<Class> findClassesByFkMajor_Name(@NotNull String name);
    Optional<Class> findClassByName(@NotNull String name);
    Optional<Class> findClassByCodeName(@NotNull String codeName);
}

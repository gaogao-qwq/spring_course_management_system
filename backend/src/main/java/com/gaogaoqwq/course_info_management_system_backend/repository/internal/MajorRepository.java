package com.gaogaoqwq.course_info_management_system_backend.repository.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Long> {
    boolean existsByCodeName(@NotNull String codeName);
    boolean existsByName(@NotNull String name);
    Optional<Major> findMajorByName(@NotNull String name);
    Optional<Major> findMajorByCodeName(@NotNull String codeName);
}

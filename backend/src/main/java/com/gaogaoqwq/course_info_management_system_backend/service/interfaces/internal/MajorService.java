package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MajorService {
    Major save(@NotNull Major major) throws SQLException, ParamException;

    long getMajorCount();

    List<Major> findMajorList();

    Optional<Major> findMajorById(@NotNull Long id);

    Optional<Major> findMajorByCodeName(@NotNull String codeName);

    Optional<Major> findMajorByName(@NotNull String name);

    Major update(@NotNull Major major) throws SQLException;

}

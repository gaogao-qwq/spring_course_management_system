package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClassService {

    Class save(@NotNull Class cls, @NotNull Major major) throws SQLException, ParamException;

    List<Class> findClassList();

    List<Class> findClassesByMajorId(@NotNull Long id);

    List<Class> findClassesByMajorName(@NotNull String name);

    Optional<Class> findClassById(@NotNull Long id);

    Optional<Class> findClassByName(@NotNull String name);

    Optional<Class> findClassByCodeName(@NotNull String codeName);

    Class updateClass(@NotNull Class cls) throws QueryException;

    void delete(@NotNull Long id) throws QueryException;

}

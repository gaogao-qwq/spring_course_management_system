package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher save(@NotNull Teacher teacher) throws SQLException, ParamException;

    List<Teacher> findTeacherList();

    List<Teacher> findTeachersByName(@NotNull String name);

    List<Teacher> findTeachersByGender(@NotNull Short gender);

    Optional<Teacher> findTeacherById(@NotNull Long id);

    Optional<Teacher> findTeacherByCodeName(@NotNull String codeName);

    Teacher update(@NotNull Teacher teacher) throws QueryException;

    void delete(@NotNull Long id) throws QueryException;
}

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

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.TeacherRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher save(@NotNull Teacher teacher) throws SQLException, ParamException {
        if (teacherRepository.existsById(teacher.getId())) {
            throw new SQLException("教师 id 冲突");
        }
        if (teacherRepository.existsByCodeName(teacher.getCodeName())) {
            throw new SQLException("教室代码冲突");
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public long getTeacherCount() {
        return teacherRepository.count();
    }

    @Override
    public List<Teacher> findTeacherList() {
        return teacherRepository.findAll();
    }

    @Override
    public Page<Teacher> getTeacherByPage(@NotNull Integer page, @NotNull Integer size) {
        return teacherRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Teacher> findTeachersByName(@NotNull String name) {
        return teacherRepository.findTeachersByName(name);
    }

    @Override
    public List<Teacher> findTeachersByGender(@NotNull Short gender) {
        return teacherRepository.findTeachersByGenderOrderByGenderAsc(gender);
    }

    @Override
    public Optional<Teacher> findTeacherById(@NotNull Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherByCodeName(@NotNull String codeName) {
        return teacherRepository.findTeacherByCodeName(codeName);
    }

    @Override
    public Teacher update(@NotNull Teacher teacher) throws QueryException {
        if (!teacherRepository.existsById(teacher.getId())) {
            throw new QueryException("未找到对应教师");
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(@NotNull Long id) throws QueryException {
        Optional<Teacher> opt = teacherRepository.findById(id);
        if (opt.isEmpty()) {
            throw new QueryException("未找到对应教师");
        }
        teacherRepository.delete(opt.get());
    }
}

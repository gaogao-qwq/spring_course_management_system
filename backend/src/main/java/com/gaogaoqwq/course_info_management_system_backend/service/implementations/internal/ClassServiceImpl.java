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

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.ClassRepository;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.MajorRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.ClassService;
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
public class ClassServiceImpl implements ClassService {

    final private ClassRepository classRepository;
    final private MajorRepository majorRepository;

    @Override
    public Class save(@NotNull Class cls, @NotNull Major major) throws SQLException, ParamException {
        if (classRepository.existsById(cls.getId())) {
            throw new SQLException("班级 id 冲突");
        }
        if (classRepository.existsByName(cls.getName())) {
            throw new SQLException("班级名冲突");
        }
        if (classRepository.existsByCodeName(cls.getCodeName())) {
            throw new SQLException("班级代码冲突");
        }
        Optional<Major> opt = majorRepository.findById(major.getId());
        if (opt.isEmpty()) {
            throw new QueryException("未找到相应的专业");
        }
        cls.setFkMajor(opt.get());
        return classRepository.save(cls);
    }

    @Override
    public long getClassCount() {
        return classRepository.count();
    }

    @Override
    public List<Class> findClassList() {
        return classRepository.findAll();
    }

    @Override
    public Page<Class> getClassByPage(@NotNull Integer page, @NotNull Integer size) {
        return classRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Class> findClassesByMajorId(@NotNull Long id) {
        return classRepository.findClassesByFkMajor_Id(id);
    }

    @Override
    public List<Class> findClassesByMajorName(@NotNull String name) {
        return classRepository.findClassesByFkMajor_Name(name);
    }

    @Override
    public Optional<Class> findClassById(@NotNull Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Optional<Class> findClassByName(@NotNull String name) {
        return classRepository.findClassByName(name);
    }

    @Override
    public Optional<Class> findClassByCodeName(@NotNull String codeName) {
        return classRepository.findClassByCodeName(codeName);
    }

    @Override
    public Class updateClass(@NotNull Class cls) throws QueryException {
        if (!classRepository.existsById(cls.getId())) {
            throw new QueryException("未找到对应班级");
        }
        return classRepository.save(cls);
    }

    @Override
    public void delete(@NotNull Long id) throws QueryException {
        Optional<Class> opt = classRepository.findById(id);
        if (opt.isEmpty()) {
            throw new QueryException("未找到对应班级");
        }
        classRepository.delete(opt.get());
    }
}
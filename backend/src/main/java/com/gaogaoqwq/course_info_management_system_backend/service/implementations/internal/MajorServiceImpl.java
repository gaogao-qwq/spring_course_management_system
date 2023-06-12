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

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.MajorRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.MajorService;
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
public class MajorServiceImpl implements MajorService {

    final private MajorRepository majorRepository;

    @Override
    public Major save(@NotNull Major major) throws SQLException, ParamException {
        if (majorRepository.existsById(major.getId())) {
            throw new SQLException("专业 id 冲突");
        }
        if (majorRepository.existsByCodeName(major.getCodeName())) {
            throw new SQLException("专业代码冲突");
        }
        if (majorRepository.existsByName(major.getName())) {
            throw new SQLException("专业名冲突");
        }
        return majorRepository.save(major);
    }

    @Override
    public long getMajorCount() {
        return majorRepository.count();
    }

    @Override
    public List<Major> findMajorList() {
        return majorRepository.findAll();
    }

    @Override
    public Page<Major> getMajorByPage(@NotNull Integer page, @NotNull Integer size) {
        return majorRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Major> findMajorById(@NotNull Long id) {
        return majorRepository.findById(id);
    }

    @Override
    public Optional<Major> findMajorByCodeName(@NotNull String codeName) {
        return majorRepository.findMajorByCodeName(codeName);
    }

    @Override
    public Optional<Major> findMajorByName(@NotNull String name) {
        return majorRepository.findMajorByName(name);
    }

    public Major update(@NotNull Major major) throws SQLException {
        if (!majorRepository.existsById(major.getId())) {
            throw new SQLException("Major not found by given parameter");
        }
        return majorRepository.save(major);
    }

}

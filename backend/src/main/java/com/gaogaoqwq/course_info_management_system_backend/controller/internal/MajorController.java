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

package com.gaogaoqwq.course_info_management_system_backend.controller.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.MajorService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/major")
public class MajorController {

    private final MajorService majorService;

    @PostMapping("/save")
    public R save(@RequestBody @NotNull Major major) throws SQLException, ParamException {
        Major mjr = majorService.save(major);
        return R.success().code(ResultCode.SUCCESS.getCode()).data(mjr);
    }

    @GetMapping("/count")
    public R count() {
        return R.success().data(majorService.getMajorCount());
    }

    @GetMapping("/")
    public R fetchByPage(@RequestParam(name = "page") Integer page,
                         @RequestParam(name = "size", required = false) @NotNull Optional<Integer> size) {
        if (size.isEmpty()) {
            size = Optional.of(10);
        }
        Page<Major> majorPage = majorService.getMajorByPage(page, size.get());
        return R.success().data(majorPage.getContent());
    }

    @GetMapping("/all")
    public R fetchMajors() {
        return R.success().data(majorService.findMajorList());
    }

    @GetMapping("/query")
    public R query(@RequestParam @NotNull Map<String, String> params) {
        if (params.isEmpty()) {
            throw new ParamException("空参数", ResultCode.PARAM_ERROR.getCode());
        }
        Set<String> singleResultParams = Set.of("id", "code_name", "major_name");
        for (var param : params.keySet()) {
            if (singleResultParams.contains(param) && params.size() > 1) {
                throw new ParamException("查询参数冲突", ResultCode.PARAM_CONFLICT.getCode());
            }
        }

        if (params.containsKey("id")) {
            Long id = Long.valueOf(params.get("id"));
            Optional<Major> opt = majorService.findMajorById(id);
            return opt.map(major -> R.success().data(major))
                    .orElseGet(() -> R.failure().code(ResultCode.NOT_FOUND.getCode()).message("未找到参数对应的专业"));
        }
        if (params.containsKey("code_name")) {
            String codeName = params.get("code_name");
            Optional<Major> opt = majorService.findMajorByCodeName(codeName);
            return opt.map(major -> R.success().data(major))
                    .orElseGet(() -> R.failure().code(ResultCode.NOT_FOUND.getCode()).message("未找到参数对应的专业"));
        }
        if (params.containsKey("name")) {
            String name = params.get("name");
            Optional<Major> opt = majorService.findMajorByName(name);
            return opt.map(major -> R.success().data(major))
                    .orElseGet(() -> R.failure().code(ResultCode.NOT_FOUND.getCode()).message("未找到参数对应的专业"));
        }
        throw new ParamException("参数错误", ResultCode.PARAM_ERROR.getCode());
    }

    @PutMapping("/update")
    public R update(@RequestBody Major major) throws SQLException {
        Major mjr = majorService.update(major);
        return R.success().data(mjr);
    }
}

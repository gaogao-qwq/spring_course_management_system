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

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Teacher;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.TeacherService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/save")
    public R save(@RequestBody @NotNull Teacher teacher) throws SQLException {
        return R.success().code(ResultCode.SUCCESS.getCode()).data(teacherService.save(teacher));
    }

    @GetMapping("/count")
    public R count() {
        return R.success().data(teacherService.getTeacherCount());
    }

    @GetMapping("/")
    public R fetchByPage(@RequestParam(name = "page") Integer page,
                         @RequestParam(name = "size", required = false) @NotNull Optional<Integer> size) {
        if (size.isEmpty()) {
            size = Optional.of(10);
        }
        Page<Teacher> teacherPage = teacherService.getTeacherByPage(page, size.get());
        return R.success().data(teacherPage.getContent());
    }

    @GetMapping("/all")
    public R fetchTeachers() {
        return R.success().code(ResultCode.SUCCESS.getCode()).data(teacherService.findTeacherList());
    }

    @GetMapping("/query")
    public R query(@RequestParam @NotNull Map<String, String> params) {
        if (params.isEmpty()) {
            throw new ParamException("空参数", ResultCode.PARAM_ERROR.getCode());
        }
        Set<String> singleResultParams = Set.of("id", "code_name");
        for (var param : params.keySet()) {
            if (singleResultParams.contains(param) && params.size() > 1) {
                throw new ParamException("查询参数冲突", ResultCode.PARAM_CONFLICT.getCode());
            }
        }

        if (params.containsKey("id")) {
            Long id = Long.valueOf(params.get("id"));
            Optional<Teacher> opt = teacherService.findTeacherById(id);
            return opt.map(teacher -> R.success().data(opt.get()))
                    .orElseThrow(() -> new QueryException("未找到参数对应的教师"));
        }
        if (params.containsKey("code_name")) {
            String codeName = params.get("code_name");
            Optional<Teacher> opt = teacherService.findTeacherByCodeName(codeName);
            return opt.map(teacher -> R.success().data(opt.get()))
                    .orElseThrow(() -> new QueryException("未找到参数对应的教师"));
        }

        Map<Teacher, Integer> teacherCnt = new HashMap<>();
        List<Teacher> result = new ArrayList<>();
        int paramCnt = 0;
        if (params.containsKey("name")) {
            paramCnt++;
            List<Teacher> teachers = teacherService.findTeachersByName(params.get("name"));
            for (var teacher : teachers) {
                if (!teacherCnt.containsKey(teacher)) {
                    teacherCnt.put(teacher, 1);
                    continue;
                }
                teacherCnt.put(teacher, teacherCnt.get(teacher)+1);
            }
        }
        if (params.containsKey("gender")) {
            paramCnt++;
            List<Teacher> teachers = teacherService.findTeachersByGender(Short.valueOf(params.get("gender")));
            for (var teacher : teachers) {
                if (!teacherCnt.containsKey(teacher)) {
                    teacherCnt.put(teacher, 1);
                    continue;
                }
                teacherCnt.put(teacher, teacherCnt.get(teacher)+1);
            }
        }

        for (var teacher : teacherCnt.keySet()) {
            if (teacherCnt.get(teacher).equals(paramCnt)) {
                result.add(teacher);
            }
        }

        if (result.isEmpty()) {
            throw new QueryException("未找到符合条件筛选的教师");
        }
        return R.success().data(result);
    }

    @PutMapping("/update")
    public R update(@RequestBody Teacher teacher) {
        Teacher tch = teacherService.update(teacher);
        return R.success().data(tch);
    }

}

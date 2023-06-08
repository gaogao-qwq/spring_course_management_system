package com.gaogaoqwq.course_info_management_system_backend.controller.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.CourseService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/save")
    public R save(@RequestBody @NotNull Course course) throws SQLException, ParamException {
        Course crs = courseService.save(course, course.getFkTeacher(), course.getFkClass());
        return R.success().code(ResultCode.SUCCESS.getCode()).data(crs);
    }

    @GetMapping("/count")
    public R count() {
        return R.success().data(courseService.getCourseCount());
    }

    @GetMapping("/all")
    public R fetchCourses() {
        return R.success().code(ResultCode.SUCCESS.getCode()).data(courseService.findCourseList());
    }

    @GetMapping("/query")
    public R query(@RequestParam @NotNull Map<String, String> params) throws SQLException, ParamException {
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
            Optional<Course> opt = courseService.findCourseById(id);
            return opt.map(student -> R.success().code(ResultCode.SUCCESS.getCode()).data(student))
                    .orElseThrow(() -> new QueryException("未找到参数对应的课程"));
        }
        if (params.containsKey("code_name")) {
            String codeName = params.get("code_name");
            Optional<Course> opt = courseService.findCourseByCodeName(codeName);
            return opt.map(student -> R.success().code(ResultCode.SUCCESS.getCode()).data(student))
                    .orElseThrow(() -> new QueryException("未找到参数对应的课程"));
        }

        Map<Course, Integer> courseCnt = new HashMap<>();
        List<Course> result = new ArrayList<>();
        int paramCnt = 0;
        if (params.containsKey("name")) {
            paramCnt++;
            List<Course> courses = courseService.findCoursesByName(params.get("name"));
            for (var course : courses) {
                courseCnt.put(course, courseCnt.get(course) == null
                        ? 1
                        : courseCnt.get(course)+1);
            }
        }
        if (params.containsKey("teacher_id")) {
            paramCnt++;
            List<Course> courses = courseService.findCoursesByTeacherId(Long.valueOf(params.get("teacher_id")));
            for (var course : courses) {
                courseCnt.put(course, courseCnt.get(course) == null
                        ? 1
                        : courseCnt.get(course)+1);
            }
        }
        if (params.containsKey("teacher_name")) {
            paramCnt++;
            List<Course> courses = courseService.findCoursesByTeacherName(params.get("teacher_name"));
            for (var course : courses) {
                courseCnt.put(course, courseCnt.get(course) == null
                        ? 1
                        : courseCnt.get(course)+1);
            }
        }
        if (params.containsKey("class_id")) {
            paramCnt++;
            List<Course> courses = courseService.findCoursesByClassId(Long.valueOf(params.get("class_id")));
            for (var course : courses) {
                courseCnt.put(course, courseCnt.get(course) == null
                        ? 1
                        : courseCnt.get(course)+1);
            }
        }
        if (params.containsKey("class_name")) {
            paramCnt++;
            List<Course> courses = courseService.findCoursesByClassName(params.get("class_name"));
            for (var course : courses) {
                courseCnt.put(course, courseCnt.get(course) == null
                        ? 1
                        : courseCnt.get(course)+1);
            }
        }

        for (var course : courseCnt.keySet()) {
            if (courseCnt.get(course).equals(paramCnt)) {
                result.add(course);
            }
        }

        if (result.isEmpty()) {
            throw new QueryException("未找到符合条件筛选的课程");
        }
        return R.success().code(ResultCode.SUCCESS.getCode()).data(result);
    }

    @PutMapping("/update")
    public R update(@RequestBody Course course) throws QueryException {
        Course crs = courseService.update(course);
        return R.success().code(ResultCode.SUCCESS.getCode()).data(crs);
    }

}

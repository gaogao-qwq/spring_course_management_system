package com.gaogaoqwq.course_info_management_system_backend.controller.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import com.gaogaoqwq.course_info_management_system_backend.exception.ParamException;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.StudentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public R save(@RequestBody Student student) throws SQLException, ParamException {
        Student std = studentService.save(student, student.getFkClass());
        return R.success().code(ResultCode.SUCCESS.getCode()).data(std);
    }

    @GetMapping("/all")
    public R fetchStudents() {
        return R.success().code(ResultCode.SUCCESS.getCode()).data(studentService.findStudentList());
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
            Optional<Student> opt = studentService.findStudentById(id);
            return opt.map(student -> R.success().code(ResultCode.SUCCESS.getCode()).data(student))
                    .orElseThrow(() -> new QueryException("未找到参数对应的学生"));
        }
        if (params.containsKey("code_name")) {
            String codeName = params.get("code_name");
            Optional<Student> opt = studentService.findStudentByCodeName(codeName);
            return opt.map(student -> R.success().code(ResultCode.SUCCESS.getCode()).data(student))
                    .orElseThrow(() -> new QueryException("未找到参数对应的学生"));
        }

        Map<Student, Integer> studentCnt = new HashMap<>();
        List<Student> result = new ArrayList<>();
        int paramCnt = 0;
        if (params.containsKey("name")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByName(params.get("name"));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("gender")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByGender(Short.valueOf(params.get("gender")));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("class_id")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByClassId(Long.valueOf(params.get("class_id")));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("class_name")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByClassName(params.get("class_name"));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("major_id")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByMajorId(Long.valueOf(params.get("major_id")));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("major_name")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByMajorName(params.get("major_name"));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }
        if (params.containsKey("course_id")) {
            paramCnt++;
            List<Student> students = studentService.findStudentsByCourseId(Long.valueOf(params.get("course_id")));
            for (var student : students) {
                studentCnt.put(student, studentCnt.get(student) == null
                        ? 1
                        : studentCnt.get(student)+1);
            }
        }

        for (var student : studentCnt.keySet()) {
            if (studentCnt.get(student).equals(paramCnt)) {
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            throw new QueryException("未找到符合条件筛选的学生");
        }
        return R.success().code(ResultCode.SUCCESS.getCode()).data(result);
    }

    @PutMapping("/update")
    public R UpdateStudent(@RequestParam Student student) throws QueryException {
        Student std = studentService.update(student);
        return R.success().code(ResultCode.SUCCESS.getCode()).data(std);
    }

}

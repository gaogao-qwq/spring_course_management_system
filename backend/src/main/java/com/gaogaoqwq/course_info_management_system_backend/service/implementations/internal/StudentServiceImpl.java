package com.gaogaoqwq.course_info_management_system_backend.service.implementations.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.ClassRepository;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.StudentRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal.StudentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    // 不建议使用 @Autowired 注解
    final private StudentRepository studentRepository;
    final private ClassRepository classRepository;

    @Override
    public Student save(@NotNull Student student, @NotNull Class cls) throws SQLException, QueryException {
        if (studentRepository.existsById(student.getId())) {
            throw new SQLException("学生 id 冲突");
        }
        Optional<Class> classOptional = classRepository.findById(cls.getId());
        if (classOptional.isEmpty()) {
            throw new QueryException("未找到参数对应班级");
        }
        student.setFkClass(classOptional.get());
        return studentRepository.save(student);
    }

    @Override
    public long getStudentCount() {
        return studentRepository.count();
    }

    @Override
    public List<Student> findStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(@NotNull Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findStudentByCodeName(@NotNull String codeName) {
        return studentRepository.findStudentByCodeName(codeName);
    }

    @Override
    public List<Student> findStudentsByName(@NotNull String name) {
        return studentRepository.findStudentsByName(name);
    }

    @Override
    public List<Student> findStudentsByGender(@NotNull Short gender) {
        return studentRepository.findStudentsByGender(gender);
    }

    @Override
    public List<Student> findStudentsByClassId(@NotNull Long id) {
        return studentRepository.findStudentsByFkClass_Id(id);
    }

    @Override
    public List<Student> findStudentsByClassName(@NotNull String name) {
        return studentRepository.findStudentsByFkClass_Name(name);
    }

    @Override
    public List<Student> findStudentsByMajorId(@NotNull Long id) {
        return studentRepository.findStudentsByFkClass_FkMajor_Id(id);
    }

    @Override
    public List<Student> findStudentsByMajorName(@NotNull String name) {
        return studentRepository.findStudentsByFkClass_FkMajor_Name(name);
    }

    @Override
    public List<Student> findStudentsByCourseId(@NotNull Long id) {
        return studentRepository.findStudentsByFkClass_FkCourse_Id(id);
    }

    @Override
    public Student update(@NotNull Student student) throws QueryException {
        if (!studentRepository.existsById(student.getId())) {
            throw new QueryException("未找到对应学生");
        }
        return studentRepository.save(student);
    }

    @Override
    public void delete(@NotNull Long id) throws SQLException {
        Optional<Student> opt = studentRepository.findById(id);
        if (opt.isEmpty()) {
            throw new SQLException("Student not found by given id");
        }
        studentRepository.delete(opt.get());
    }
}

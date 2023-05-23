package com.gaogaoqwq.course_info_management_system_backend;

import com.gaogaoqwq.course_info_management_system_backend.constant.internal.Gender;
import com.gaogaoqwq.course_info_management_system_backend.constant.user.Role;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import com.gaogaoqwq.course_info_management_system_backend.repository.internal.StudentRepository;
import com.gaogaoqwq.course_info_management_system_backend.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    final private UserRepository userRepository;
    final private StudentRepository studentRepository;

    final private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Student std1 = Student.builder()
                .id(0L)
                .codeName("10001")
                .name("Pixy")
                .gender(Gender.Male.value())
                .birthDate(new Date())
                .admissionDate(new Date())
                .build();

        Student std2 = Student.builder()
                .id(0L)
                .codeName("10002")
                .name("Mobius")
                .gender(Gender.Male.value())
                .birthDate(new Date())
                .admissionDate(new Date())
                .build();

        Student std3 = Student.builder()
                .id(0L)
                .codeName("10003")
                .name("Xiu Xu")
                .gender(Gender.Female.value())
                .birthDate(new Date())
                .admissionDate(new Date())
                .build();

        User usr1 = User.builder()
                .id(0L)
                .username("admin")
                .password(this.passwordEncoder.encode("admin"))
                .roles(List.of(Role.ADMIN.getValue()))
                .build();

        User usr2 = User.builder()
                .id(0L)
                .username("teacher")
                .password(this.passwordEncoder.encode("teacher"))
                .roles(List.of(Role.TEACHER.getValue()))
                .build();

        User usr3 = User.builder()
                .id(0L)
                .username("student")
                .password(this.passwordEncoder.encode("student"))
                .roles(List.of(Role.STUDENT.getValue()))
                .build();

        if (!userRepository.existsById(1L)) {
            userRepository.save(usr1);
            userRepository.save(usr2);
            userRepository.save(usr3);
        }
    }

}

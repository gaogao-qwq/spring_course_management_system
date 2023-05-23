package com.gaogaoqwq.course_info_management_system_backend.entity.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(
    name = "teachers",
    indexes = {
        @Index(name = "teacher_code_name_index", columnList = "teacher_code_name", unique = true),
        @Index(name = "teacher_name_index", columnList = "teacher_name") })
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_code_name", unique = true, nullable = false)
    private String codeName;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    @Column(name = "teacher_gender", nullable = false)
    private Short gender;

    @Column(name = "teacher_birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "teacher_admission_date", nullable = false)
    private Date admissionDate;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy="fkTeacher", fetch = FetchType.LAZY)
    private Set<Course> fkCourse;
}

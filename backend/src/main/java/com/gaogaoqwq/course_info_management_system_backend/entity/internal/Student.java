package com.gaogaoqwq.course_info_management_system_backend.entity.internal;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "students",
    indexes = {
        @Index(name = "student_code_name_index", columnList = "student_code_name", unique = true),
        @Index(name = "student_name_index", columnList = "student_name") })
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code_name", unique = true, nullable = false)
    private String codeName;

    @Column(name = "student_name", nullable = false)
    public String name;

    @Column(name = "student_gender", nullable = false)
    private Short gender;

    @Column(name = "student_birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "student_admission_date", nullable = false)
    private Date admissionDate;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Class fkClass;
}

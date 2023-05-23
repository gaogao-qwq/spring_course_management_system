package com.gaogaoqwq.course_info_management_system_backend.entity.internal;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "courses",
    indexes = {
        @Index(name = "course_code_name_index", columnList = "course_code_name", unique = true),
        @Index(name = "course_name_index", columnList = "course_name") })
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_code_name", unique = true, nullable = false)
    private String codeName;

    @Column(name = "course_name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Teacher fkTeacher;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Class fkClass;
}

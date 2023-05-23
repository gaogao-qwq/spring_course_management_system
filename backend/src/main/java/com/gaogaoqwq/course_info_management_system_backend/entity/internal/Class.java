package com.gaogaoqwq.course_info_management_system_backend.entity.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "classes",
    indexes = {
        @Index(name = "class_code_name_index", columnList = "class_code_name", unique = true),
        @Index(name = "class_name_index", columnList = "class_name", unique = true) })
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_code_name", unique = true, nullable = false)
    private String codeName;

    @Column(name = "class_name", unique = true, nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Major fkMajor;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "fkClass", fetch = FetchType.LAZY)
    private Set<Student> fkStudent;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "fkClass", fetch = FetchType.LAZY)
    private Set<Course> fkCourse;

}

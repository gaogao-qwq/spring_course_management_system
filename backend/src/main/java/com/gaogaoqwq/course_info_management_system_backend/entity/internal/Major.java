package com.gaogaoqwq.course_info_management_system_backend.entity.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "majors",
    indexes = {
        @Index(name = "major_code_name_index", columnList = "major_code_name", unique = true),
        @Index(name = "major_name_index", columnList = "major_name", unique = true) })
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "major_code_name", unique = true, nullable = false)
    private String codeName;

    @Column(name = "major_name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy="fkMajor", fetch = FetchType.LAZY)
    private Set<Class> fkClass;
}

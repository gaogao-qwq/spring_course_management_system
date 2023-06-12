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

package com.gaogaoqwq.course_info_management_system_backend.entity.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

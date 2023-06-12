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
    private String name;

    @Column(name = "student_gender", nullable = false)
    private Short gender;

    @Column(name = "student_birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "student_admission_date", nullable = false)
    private Date admissionDate;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Class fkClass;
}

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

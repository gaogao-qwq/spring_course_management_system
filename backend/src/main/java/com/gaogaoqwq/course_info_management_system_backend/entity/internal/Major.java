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

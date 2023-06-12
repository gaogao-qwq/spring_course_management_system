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

package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.internal;

import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Course;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Student;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Class;
import com.gaogaoqwq.course_info_management_system_backend.entity.internal.Major;
import com.gaogaoqwq.course_info_management_system_backend.exception.QueryException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    /**
     * 新增学生数据至 students 数据表。
     * @param student 要新增的 {@link Student} 对象与其隶属于的 {@link Class} 对象。
     * @return 返回刚刚新增的 {@link Student} 对象。
     * @throws SQLException 当表中已存在 {@literal id} 为 {@literal student.id} 的行时。
     * @throws RuntimeException 当出现死锁
     * */
    Student save(@NotNull Student student, @NotNull Class cls) throws SQLException, QueryException;

    /**
     * 从 students 数据表中返回全部数据。
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentList();

    long getStudentCount();

    Page<Student> getStudentByPage(@NotNull Integer page, @NotNull Integer size);

    /**
     * 在 students 数据表中查询并返回非独特属性 {@link Student#name} 与给定的
     * {@literal name} 相符的 {@link Student} 列表。
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByName(@NotNull String name);

    /**
     * 在 students 数据表中查询并返回非独特属性 {@link Student#gender} 与给定的
     * {@literal gender} 相符的 {@link Student} 列表。
     * @param gender 给定的 gender
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByGender(@NotNull Short gender);

    /**
     * 在 students 数据表中查询并返回独特属性 {@link Class#id}
     * 与给定的 {@literal id} 相符的 {@link Student} 列表。
     * @param id 给定的 id
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByClassId(@NotNull Long id);

    /**
     * 在 students 数据表中查询并返回独特属性 {@link Class#name}
     * 与给定的 {@literal name} 相符的 {@link Student} 列表。
     * @param name 给定的 name
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByClassName(@NotNull String name);

    /**
     * 在 students 数据表中查询并返回独特属性 {@link Major#id}
     * 与给定的 {@literal id} 相符的 {@link Student} 列表。
     * @param id 给定的 id
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByMajorId(@NotNull Long id);

    /**
     * 在 students 数据表中查询并返回独特属性 {@link Major#name}
     * 与给定的 {@literal name} 相符的 {@link Student} 列表。
     * @param name 给定的 name
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByMajorName(@NotNull String name);

    /**
     * 在 students 数据表中查询并返回独特属性 {@link Course#id}
     * 与给定的 {@literal id} 相符的 {@link Student} 列表。
     * @param id 给定的 id
     * @return 返回 {@link List}<{@link Student}>。
     * */
    List<Student> findStudentsByCourseId(@NotNull Long id);

    /**
     * 根据 {@link Student#id} 在 students 数据表中查询并返回对应的 {@link Optional}<{@link Student}>。
     * @return 返回 {@link Optional}<{@link Student}>。
     * */
    Optional<Student> findStudentById(@NotNull Long id);

    /**
     * 根据 {@link Student#codeName} 在 students 数据表中查询并返回对应的 {@link Optional}<{@link Student}>。
     * @param codeName 给定的 codeName
     * @return 返回 {@link Optional}<{@link Student}>。
     * */
    Optional<Student> findStudentByCodeName(@NotNull String codeName);

    /**
     * 在 students 数据表中更新 {@link Student} 对象。
     * @param student 要更新的 {@link Student} 对象。
     * @return 返回更新的对象。
     * @throws QueryException 当找不到对应的 {@link Student} 对象时。
     * */
    Student update(@NotNull Student student) throws QueryException;

    /**
     * 根据 {@link Student#id} 从 students 数据表中删除 {@link Student} 对象，
     * 然而 REST 思想指导下一般不会删除数据库内数据。
     * @param id 要删除的 {@link Student} 对象的 {@literal id}。
     * @throws SQLException 当找不到 {@literal id} 对应的 {@link Student} 对象时。
     * */
    void delete(@NotNull Long id) throws SQLException;

}
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

package com.gaogaoqwq.course_info_management_system_backend.controller.internal.param;

import lombok.Getter;

@Getter
public enum StudentParam {
    ID("id", true),
    CODE_NAME("code_name", true),
    NAME("name", false),
    GENDER("gender", false),
    BIRTH_DATE("birth_date", false),
    ADMISSION_DATE("admission_date", false),
    CLASS_ID("class_id", false),
    CLASS_NAME("class_name", false),
    MAJOR_ID("major_id", false),
    MAJOR_NAME("major_name", false);

    private final String paramName;

    private final boolean isSingleResult;

    StudentParam(String paramName, boolean isSingleResult) {
        this.paramName = paramName;
        this.isSingleResult = isSingleResult;
    }
}

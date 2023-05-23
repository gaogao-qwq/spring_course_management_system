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

package com.gaogaoqwq.course_info_management_system_backend.constant.internal;

public enum Gender {
    Male((short)0),
    Female((short)1),
    Other((short)2);

    final private Short value;
    Gender(Short value) {
        this.value = value;
    }

    public short value() {
        return this.value;
    }
}

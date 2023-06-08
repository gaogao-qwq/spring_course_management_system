package com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;


public interface UserService {
    Map<Object, Object> getUserInfo(UserDetails userDetails);
}

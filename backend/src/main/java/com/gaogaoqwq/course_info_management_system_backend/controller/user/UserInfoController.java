package com.gaogaoqwq.course_info_management_system_backend.controller.user;

import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/")
    public R currentUser(@NotNull @AuthenticationPrincipal UserDetails userDetails) {
        return R.success().data(userService.getUserInfo(userDetails));
    }

    @GetMapping("/count")
    public R count() {
        return R.success().data(userService.getUserCount());
    }

    @PostMapping("/update")
    public R updateUser(@RequestBody @NotNull Map<String, String> request)
            throws RuntimeException {
        return R.success().data(userService.updateUserInfo(request));
    }
}
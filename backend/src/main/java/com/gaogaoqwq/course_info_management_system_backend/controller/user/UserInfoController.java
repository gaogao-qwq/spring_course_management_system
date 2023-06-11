package com.gaogaoqwq.course_info_management_system_backend.controller.user;

import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/me")
    public R currentUser(@NotNull @AuthenticationPrincipal UserDetails userDetails) {
        return R.success().data(userService.getUserInfo(userDetails));
    }

    @GetMapping("/")
    public R fetchByPage(@RequestParam(name = "page") Integer page,
                         @RequestParam(name = "size", required = false) @NotNull Optional<Integer> size) {
        if (size.isEmpty()) {
            size = Optional.of(10);
        }
        List<Map<Object, Object>> userPage = userService.getUserInfoByPage(page, size.get());
        return R.success().data(userPage);
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
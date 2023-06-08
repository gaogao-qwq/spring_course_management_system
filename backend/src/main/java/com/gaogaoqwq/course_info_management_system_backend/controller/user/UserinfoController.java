package com.gaogaoqwq.course_info_management_system_backend.controller.user;

import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import com.gaogaoqwq.course_info_management_system_backend.repository.user.UserRepository;
import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.response.ResultCode;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserinfoController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/")
    public R currentUser(@NotNull @AuthenticationPrincipal UserDetails userDetails) {
        return R.success().data(userService.getUserInfo(userDetails));
    }

    @PostMapping("/update")
    public R updateUser(@RequestBody @NotNull Map<String, String> request)
            throws RuntimeException {
        String username = request.get("username");
        String oldPassword = request.get("old_password");
        String newPassword = request.get("new_password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, oldPassword));

        User user = userRepository.findUserByUsername(
                ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername())
                .orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        User entity = userRepository.save(user);
        return R.success().code(ResultCode.SUCCESS.getCode()).data(entity);
    }
}
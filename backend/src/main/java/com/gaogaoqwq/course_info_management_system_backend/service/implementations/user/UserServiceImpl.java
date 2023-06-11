package com.gaogaoqwq.course_info_management_system_backend.service.implementations.user;

import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import com.gaogaoqwq.course_info_management_system_backend.repository.user.UserRepository;
import com.gaogaoqwq.course_info_management_system_backend.service.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<Object, Object> getUserInfo(@NotNull UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
        );
        return model;
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public Page<User> getUserByPage(@NotNull Integer page, @NotNull Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User updateUserInfo(@NotNull Map<String, String> request) {
        String username = request.get("username");
        String oldPassword = request.get("old_password");
        String newPassword = request.get("new_password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, oldPassword));

        User user = userRepository.findUserByUsername(
                        ((org.springframework.security.core.userdetails.User) authentication.getPrincipal())
                                .getUsername())
                .orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }
}

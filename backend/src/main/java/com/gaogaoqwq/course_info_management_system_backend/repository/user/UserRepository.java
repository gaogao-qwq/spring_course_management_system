package com.gaogaoqwq.course_info_management_system_backend.repository.user;

import com.gaogaoqwq.course_info_management_system_backend.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotNull String username);
    Optional<User> findUserByUsername(@NotNull String username);
}

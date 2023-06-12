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

package com.gaogaoqwq.course_info_management_system_backend.controller.user;

import com.gaogaoqwq.course_info_management_system_backend.response.R;
import com.gaogaoqwq.course_info_management_system_backend.security.jwt.JwtProperties;
import com.gaogaoqwq.course_info_management_system_backend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @PostMapping("/login")
    public R login(@RequestBody @NotNull AuthenticationRequest request)
            throws AuthenticationException {
        String username = request.getUsername();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        String token = jwtTokenProvider.createToken(authentication);
        Map<String, Object> data = Map.of(
                "username", username,
                "token", token,
                "validityInMs", jwtProperties.getValidityInMs()
        );
        return R.success().data(data);
    }

}

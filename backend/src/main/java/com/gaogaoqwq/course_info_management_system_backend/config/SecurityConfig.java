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

package com.gaogaoqwq.course_info_management_system_backend.config;

import com.gaogaoqwq.course_info_management_system_backend.repository.user.UserRepository;
import com.gaogaoqwq.course_info_management_system_backend.security.jwt.JwtTokenAuthenticationFilter;
import com.gaogaoqwq.course_info_management_system_backend.security.jwt.JwtTokenProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain springWebFilterChain(@NotNull HttpSecurity http,
                                             JwtTokenProvider tokenProvider) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/student/**").hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.GET, "/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/class/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/course/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/major/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    UserDetailsService customUserDetailsService(UserRepository users) throws UsernameNotFoundException {
        return (username) -> users.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户：" + username + " 未找到"));
    }

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder)
            throws AuthenticationException {
        return authentication -> {
            String username = String.valueOf(authentication.getPrincipal());
            String password = String.valueOf(authentication.getCredentials());

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("签名错误");
            }

            if (!user.isEnabled()) {
                throw new DisabledException("用户已被停用");
            }

            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        };
    }

}
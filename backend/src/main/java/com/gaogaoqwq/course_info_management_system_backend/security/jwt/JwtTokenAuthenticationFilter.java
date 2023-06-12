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

package com.gaogaoqwq.course_info_management_system_backend.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    public static final String HEADER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        Optional<String> token = resolveToken((HttpServletRequest) req);
        log.info("Extracting token from HttpServletRequest: {}", token);

        if (token.isPresent() && jwtTokenProvider.validateToken(token.get())) {
            Authentication auth = jwtTokenProvider.getAuthentication(token.get());

            if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(req, res);
    }

    private Optional<String> resolveToken(@NotNull HttpServletRequest request) {
        Enumeration<String> list = request.getHeaderNames();
        log.info("HeaderNames: {}", list);
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken == null ||  bearerToken.isEmpty()) {
            String cookies = request.getHeader("cookie");
            if (cookies == null) {
                return Optional.empty();
            }
            int tokenIndex = cookies.indexOf("token=");
            bearerToken = HEADER_PREFIX + cookies.substring(tokenIndex+6, cookies.indexOf(';'));
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

}

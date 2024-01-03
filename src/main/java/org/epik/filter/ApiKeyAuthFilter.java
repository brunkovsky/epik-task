package org.epik.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${security.api.key}")
    private String apiKey;

    @Value("${security.api.header}")
    String apiHeader;

    @Value("${server.host}")
    String host;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = new Date().getTime();
        if (allowedToExecute(request)) {
            filterChain.doFilter(request, response);
            long end = new Date().getTime();
            log.info(request.getRequestURI() + ": " + (end - start) + "ms");
        } else {
            log.error(HttpStatus.UNAUTHORIZED.toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(HttpStatus.UNAUTHORIZED.toString());
        }
    }

    private boolean allowedToExecute(HttpServletRequest request) {
        boolean isSwaggerRequest = !request.getRequestURI().startsWith(host);
        boolean isAuthenticated = apiKey.equals(request.getHeader(apiHeader));
        return isSwaggerRequest || isAuthenticated;
    }

}

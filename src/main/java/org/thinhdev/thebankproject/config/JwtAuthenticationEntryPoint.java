package org.thinhdev.thebankproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException
    {
        // Set the response status to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Set the content type and error message
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errorResponse = Map.of(
                "error", "Unauthorized JwtAuthenticationEntryPoint",
                "message", authException.getMessage()
        );
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        logger.warn("Unauthorized access attempt: {}", request.getRequestURI());
    }
}

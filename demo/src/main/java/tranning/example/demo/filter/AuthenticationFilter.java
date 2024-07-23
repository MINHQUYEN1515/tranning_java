package tranning.example.demo.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String Authorization = request.getHeader("Authorization");
    if (Authorization != null) {
      Authorization = Authorization.substring(7);
    }
    logger.info("Successfully authenticated user " + Authorization);
    filterChain.doFilter(request, response);
  }
}

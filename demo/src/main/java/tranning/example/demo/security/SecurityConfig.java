package tranning.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String[] PUBLIC_ROUTE = {
            "/api/v1/user/signup"
    };

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (request) -> request.requestMatchers(PUBLIC_ROUTE).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        // http.cors(AbstractHttpConfigurer::disable);
        return http.build();

    }
}

package tranning.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import tranning.example.demo.filter.AuthenticationFilter;
import tranning.example.demo.utils.CustomDecoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        public static final String[] PUBLIC_ROUTE = {
                        "/api/v1/user/signup",
                        "/api/v1/user/login",
                        "/api/v1/user/image/**",
                        "/api/v1/user/logout",
                        "/api/v1/user/delete",
                        "api/v1/yard/image/**",
                        "api/v1/yard/getAll",
                        "api/v1/order/order-yard",
                        "/api/v1/report/delete-cache",
                        "/api/v1/report/test"

        };
        @Autowired
        private CustomDecoder customJwtDecoder;

        @Bean
        public SecurityFilterChain security(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(
                                (request) -> request.requestMatchers(PUBLIC_ROUTE).permitAll()
                                                .anyRequest().authenticated()

                )
                                .sessionManagement(sessionManagementConfig -> sessionManagementConfig
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // http.exceptionHandling(Customizer.withDefaults());

                http.csrf(AbstractHttpConfigurer::disable);
                http.httpBasic(Customizer.withDefaults());
                http.exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
                // http.cors(AbstractHttpConfigurer::disable);
                http.addFilterBefore(new AuthenticationFilter(),
                                BasicAuthenticationFilter.class);
                http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfig -> jwtConfig.decoder(customJwtDecoder)));
                return http.build();

        }

        @Bean
        public CorsFilter corsFilter() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*");
                configuration.addAllowedMethod("*");
                configuration.addAllowedHeader("*");
                UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
                basedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
                return new CorsFilter(basedCorsConfigurationSource);
        }

}

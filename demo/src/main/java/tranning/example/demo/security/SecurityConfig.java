package tranning.example.demo.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import tranning.example.demo.filter.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        public static final String[] PUBLIC_ROUTE = {
                        "/api/v1/user/signup",
                        "/api/v1/user/login",
                        "/api/v1/user/image/**"

        };
        private final String SECRET_KEY = "Yn7MAHdm9Gi9QnTk6F5jCsQatUNJW8zp";

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
                http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfig -> jwtConfig.decoder(jwtDecoder())));
                return http.build();

        }

        @Bean
        JwtDecoder jwtDecoder() {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS256");
                return NimbusJwtDecoder
                                .withSecretKey(secretKeySpec)
                                .macAlgorithm(MacAlgorithm.HS256)
                                .build();

        }

}

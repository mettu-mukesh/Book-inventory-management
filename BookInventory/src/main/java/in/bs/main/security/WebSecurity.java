package in.bs.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
                requests
                        .requestMatchers("/books/register", "/books/user", "/books/delete/user", "/favicon.ico").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow access to Swagger UI
                        .requestMatchers("/books/**").authenticated() // Secure all book endpoints
                        .anyRequest().authenticated() // Secure all other requests
        );

        http.httpBasic(httpbasic -> httpbasic.init(http)); // Enable basic authentication
        http.csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in this example
        return http.build();
    }
}

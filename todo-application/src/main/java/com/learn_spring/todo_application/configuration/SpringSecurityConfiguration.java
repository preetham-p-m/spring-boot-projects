package com.learn_spring.todo_application.configuration;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        var userDeatil1 = createNewUser("user_1", "password");
        var userDeatil2 = createNewUser("user_2", "password");

        return new InMemoryUserDetailsManager(userDeatil1, userDeatil2);
    }

    private UserDetails createNewUser(String userName, String password) {
        Function<String, String> encoder = input -> passwordEncoder().encode(input);

        return User
                .builder()
                .passwordEncoder(encoder)
                .username(userName)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(withDefaults());

        // httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable());
        // httpSecurity.headers(headersCustomizer -> headersCustomizer
        // .frameOptions(frameOptionsCustomizer -> frameOptionsCustomizer.disable()));

        // Disable CSRF protection (if needed)
        httpSecurity.csrf().disable();

        // Disable frame options using the recommended method in Spring Security 6.1
        httpSecurity.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return httpSecurity.build();
    }

}

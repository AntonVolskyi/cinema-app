package cinema.config;

import cinema.model.Role;
import cinema.service.impl.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = Role.RoleName.USER.name();
    private static final String ADMIN = Role.RoleName.ADMIN.name();

    private CustomUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(USER)
                .antMatchers(HttpMethod.GET, "/cinema-halls",
                        "/movies",
                        "/movie-sessions/available").hasAnyRole(USER, ADMIN)
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, "/orders", "/shopping-carts/by-user").hasRole(USER)
                .antMatchers(HttpMethod.PUT, "/movie-sessions/**").hasRole(ADMIN)
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole(USER)
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}

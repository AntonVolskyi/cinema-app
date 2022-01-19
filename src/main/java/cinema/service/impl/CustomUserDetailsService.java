package cinema.service.impl;

import cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        cinema.model.User user = userService.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("Can`t find user with email: " + email));
        User.UserBuilder builder = User.withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}

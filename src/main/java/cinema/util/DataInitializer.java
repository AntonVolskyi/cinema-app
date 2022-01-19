package cinema.util;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private UserService userService;
    private RoleService roleService;

    @PostConstruct
    public void init() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User adminUser = new User();
        adminUser.setEmail("bob@gmail.com");
        adminUser.setPassword("12345");
        adminUser.setRoles(Set.of(adminRole, userRole));
        userService.add(adminUser);
        User user = new User();
        user.setEmail("ani@gmail.com");
        user.setPassword("qwerty");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}

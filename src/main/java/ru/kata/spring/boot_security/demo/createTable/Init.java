package ru.kata.spring.boot_security.demo.createTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializeDB() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();
        adminRole.add(roleService.findById(1L));
        userRole.add(roleService.findById(2L));
        allRoles.add(roleService.findById(1L));
        allRoles.add(roleService.findById(2L));
        userService.saveUser(new User("admin", "andrey", 123L,"100", adminRole));
        userService.saveUser(new User("user", "memb", 1223L,"100", userRole));
        userService.saveUser(new User("lol", "bomb", 12342L,"100", allRoles));
    }
}

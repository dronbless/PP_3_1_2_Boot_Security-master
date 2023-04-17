package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private  UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "all-users";
    }
    @GetMapping("/createuser")
    public String createUserForm(Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("roles",roleService.findAll());
        return "createuser";
    }

    @PostMapping("/createuser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.findAll());
        return "user-update";
    }


    @PatchMapping("/user-update")
    public String update(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
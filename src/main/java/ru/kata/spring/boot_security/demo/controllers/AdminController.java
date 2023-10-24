package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService,
                           RegistrationService registrationService, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService);
        return "/admin/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("selectedRole") String selectedRole) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        registrationService.register(user, selectedRole);
        return "redirect:/admin/index";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Integer id,
                         @RequestParam("selectedRole") String selectedRole) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRole().add(new Role(selectedRole));
        userService.updateUser(id, user);
        return "redirect:/admin/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/index";
    }
}

package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userroles", roleService.getAllRoles());
        return "edit-form";
    }

    @PostMapping("/{id}")
    public String updateDb(@ModelAttribute("user") @Valid User upduser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-form";
        }
        userService.updUser(upduser);
        return "redirect:/admin";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUsr(id);
        return "redirect:/admin";
    }

    @GetMapping("/addform")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userroles", roleService.getAllRoles());
        return "new-form";
    }

    @PostMapping("/add")
    public String addNew(@ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-form";
        }
        userService.addNewUser(newUser);
        return "redirect:/admin";
    }
}

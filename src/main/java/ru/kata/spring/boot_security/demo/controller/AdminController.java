package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

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
    public String getAdminPage(Principal principal, Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("userroles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userroles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateDb(@ModelAttribute("user") @Valid User user, @PathVariable("id") int id, ModelMap model) {
        model.addAttribute("userroles", roleService.getAllRoles());
        userService.updUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUsr(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String addForm(ModelMap model, @ModelAttribute("user") User user) {
        new ModelAndView("new");
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("")
    public String addNew(@ModelAttribute("user") @Valid User newUser) {
        userService.addNewUser(newUser);
        return "redirect:/admin";
    }
}

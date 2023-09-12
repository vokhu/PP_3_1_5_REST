package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Well done!");
        model.addAttribute("messages", messages);
        return "test";
    }

    @GetMapping(value = "/user")
    public String userInfo (Model model, Principal principal) {
        User userInf = userService./*loadUserByUsername*/findByUsername(principal.getName());
        model.addAttribute("userinf", userInf);
        return "user-info";
    }

    @GetMapping("/admin")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/admin/update/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit-form";
    }

    @PostMapping("/admin/{id}")
    public String updateDb(@ModelAttribute("user")@Valid User upduser, BindingResult bindingResult,
                           @RequestParam(name = "listRoles[]", required = false) String... roles) {
        if(bindingResult.hasErrors()){
            return "edit-form";
        }
        Set<Role> roleSet = userService.getSetRoles(roles);
        upduser.setRoles(roleSet);
        userService.updUser(upduser);
        return "redirect:/admin";
    }

    @PostMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUsr(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/addform")
    public String addForm(@ModelAttribute("user") User user) {
        return "new-form";
    }


    @PostMapping("admin/add")
    public String addNew(@ModelAttribute("user") @Valid User newUser, BindingResult bindingResult,
                         @RequestParam(name = "listRoles[]", required = false) String... roles) {
        if(bindingResult.hasErrors()) {
            return "new-form";
        }
        Set<Role> roleSet = userService.getSetRoles(roles);
        newUser.setRoles(roleSet);
        userService.addNewUser(newUser);
        return "redirect:/admin";
    }
}

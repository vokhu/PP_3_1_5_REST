package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "test";
    }

    @GetMapping(value = "/user")
    public String userInfo (Model model, Principal principal) {
        User userInf = (User)userService.loadUserByUsername(principal.getName());
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
    public String updateDb(@ModelAttribute("user")@Valid User upduser, BindingResult bindingResult/*, @PathVariable("id") int id*/) {
        if(bindingResult.hasErrors()){
            return "edit-form";
        }
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
    public String addNew(@ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "new-form";
        }
        userService.addNewUser(newUser);
        return "redirect:/admin";
    }
}

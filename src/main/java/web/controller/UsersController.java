package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showAllUsers(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("users", userService.getAllUsers());

        if (id != null) {

            User userToEdit = userService.getUserById(id);
            if (userToEdit == null) {

                model.addAttribute("user", new User());

            } else {
                model.addAttribute("user", userToEdit);
            }
        } else {

            model.addAttribute("user", new User());
        }
        return "users";
    }

    @GetMapping("/users")
    public String editGet(@RequestParam(value = "id", required = false) Long id, Model model) {

        return "redirect:/" + (id != null ? "?id=" + id : "");
    }

    @PostMapping("/users")
    public String saveOrUpdateUser(@ModelAttribute("user") User user) {
        if (user.getId() == null || user.getId() == 0) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user.getId(), user);
        }
        return "redirect:/users";
    }


    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}









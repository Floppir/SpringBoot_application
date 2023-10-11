package PP._1_2.controller;

import PP._1_2.model.User;
import PP._1_2.service.UserService;
import PP._1_2.service.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    private String message;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(ModelMap model, @ModelAttribute User user) {
        model.addAttribute(userService.findAll());
        model.addAttribute("message", message);
        return "index";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        try {
            userService.save(user);
            message = "Пользователь успешно добавлен";
        } catch (ConstraintViolationException e) {
            message = "Поля ввода не могут быть пустыми";
        }

        return "redirect:/";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User user) {
        try {
            userService.update(user);
            message = "Данные пользователя обновлены";
        } catch (EntityNotFoundException e) {
            message = e.getMessage();
        } catch (RuntimeException e) {
            message = "Поля ввода не могут быть пустыми";
        }

        return "redirect:/";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@ModelAttribute User user) {
        try {
            userService.deleteById(user.getId());
            message = "Пользователь успешно удален";
        } catch (EntityNotFoundException e) {
            message = e.getMessage();
        }

        return "redirect:/";
    }
}

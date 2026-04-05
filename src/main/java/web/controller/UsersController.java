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

    // Отображает список пользователей и форму для добавления/редактирования
    // GET /users         -> отображает список и пустую форму "добавить"
    // GET /users?id=1    -> отображает список и форму "редактировать" пользователя с id=1
    @GetMapping
    public String showAllUsers(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("users", userService.getAllUsers()); // Добавляем всех пользователей для таблицы

        if (id != null) {
            // Если передан ID, то это запрос на редактирование
            User userToEdit = userService.getUserById(id);
            if (userToEdit == null) {
                // Если пользователь не найден, создаем новую пустую форму
                model.addAttribute("user", new User());
                // В реальном приложении можно добавить сообщение об ошибке
            } else {
                model.addAttribute("user", userToEdit); // Добавляем существующего пользователя в форму
            }
        } else {
            // Если ID не передан, то это запрос на добавление нового пользователя
            model.addAttribute("user", new User()); // Добавляем пустой объект для формы
        }
        return "users"; // Имя Thymeleaf-шаблона
    }
    @GetMapping("/users")
    public String editGet(@RequestParam(value = "id", required = false) Long id, Model model) {
        // Просто перенаправляем на корень с тем же id
        return "redirect:/" + (id != null ? "?id=" + id : "");
    }
    // Обрабатывает POST-запросы от формы (добавление или обновление)
    // POST /users
    @PostMapping("/users")
    public String saveOrUpdateUser(@ModelAttribute("user") User user) {
        if (user.getId() == null || user.getId() == 0) { // Если ID нет или 0, то это новый пользователь
            userService.saveUser(user);
        } else { // Иначе, это обновление существующего
            userService.updateUser(user.getId(), user);
        }
        return "redirect:/users"; // Перенаправляем на GET /users для обновления списка
    }

    // Обрабатывает POST-запросы для удаления пользователя
    // POST /users/delete?id=1
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Перенаправляем на GET /users
    }

}









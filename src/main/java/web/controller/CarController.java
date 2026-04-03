package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false,defaultValue = "0") int count, Model model) {
        model.addAttribute("cars", carService.getCars(count));
        return "cars";
    }
    @GetMapping("/")
    public String showAllCars(Model model)  {
        model.addAttribute("cars", carService.getCars(4));
        return "cars";
    }
}







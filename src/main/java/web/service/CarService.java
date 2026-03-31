package web.service;

import org.springframework.stereotype.Service;

import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final List<Car> cars;

    public CarService() {
        cars = new ArrayList<>();
        cars.add(new Car(1L, "Toyota", "Camry", 2020));
        cars.add(new Car(2L, "BMW", "X5", 2022));
        cars.add(new Car(3L, "Mercedes", "E-Class", 2021));
        cars.add(new Car(4L, "Audi", "A4", 2019));
        cars.add(new Car(5L, "Tesla", "Model Y", 2023));
    }

    public List<Car> getCars(Integer count) {
        if (count == null || count >= cars.size()) {
            return new ArrayList<>(cars);
        }
        return new ArrayList<>(cars.subList(0, count));
    }
}

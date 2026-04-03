package web.DAO;
import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CarDaoImpl implements CarDao {
    private final List<Car> cars;

    public CarDaoImpl() {
        cars = new ArrayList<>();
        cars.add(new Car(1L, "Toyota", "Camry", 2020));
        cars.add(new Car(2L, "BMW", "X5", 2022));
        cars.add(new Car(3L, "Mercedes", "E-Class", 2021));
        cars.add(new Car(4L, "Audi", "A4", 2019));
        cars.add(new Car(5L, "Tesla", "Model Y", 2023));
    }

    @Override
    public List<Car> getCars(int count) {

        if (count <= 0) {
            return new ArrayList<>(cars);
        }
                return cars.stream()
                .limit(count)
                .collect(Collectors.toList());

    }


}



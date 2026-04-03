package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.DAO.CarDao;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImpl  implements CarService {
    private CarDao carDao;
@Autowired
public CarServiceImpl(CarDao carDao) {
    this.carDao = carDao;
}

    @Override
    public List<Car> getCars(int count) {

        return carDao.getCars(count);
    }

}

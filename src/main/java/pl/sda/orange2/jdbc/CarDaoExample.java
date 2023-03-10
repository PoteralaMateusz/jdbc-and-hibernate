package pl.sda.orange2.jdbc;

import pl.sda.orange2.dao.CarDao;
import pl.sda.orange2.entity.Car;
import pl.sda.orange2.jdbc.config.H2Config;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CarDaoExample {

    public static void main(String[] args) {
        System.out.println("Car dao example");

        System.out.println("Reading all cars from db");

        try {
            var h2Connection = DriverManager.getConnection(H2Config.DB_URL, H2Config.USER, H2Config.PASSWORD);
            CarDao carDao = new CarDao(h2Connection);
            var allCarsFromDb = carDao.findAll();
            System.out.println("All cars form db " + allCarsFromDb);

            System.out.println("Finding car by id");
            System.out.println("First with existing id");
            Car existing = carDao.findById(1L);
            System.out.println(existing);
            System.out.println("Deleting car by id");
            carDao.deleteById(4L);

            allCarsFromDb = carDao.findAll();
            System.out.println("All cars form db " + allCarsFromDb);

//            Car newCarToSave = new Car(null,"black","fiat","maluch");
//            carDao.save(newCarToSave);
//            System.out.println("Cars now after adding: " + carDao.findAll());

            Car newCarToSave = new Car(3L,"Ping","BMW","X7");
            carDao.save(newCarToSave);
            System.out.println("Cars now update: " + carDao.findAll());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

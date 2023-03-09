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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

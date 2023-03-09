package pl.sda.orange2.dao;

import pl.sda.orange2.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements DataAccess<Car, Long> {

    private final Connection dbConnection;

    public CarDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void save(Car car) {
        String saveQuery;
        if (car.id() != null) {
            saveQuery = """
                    UPDATE CARS
                    SET COLOUR = ? , BRAND = ? , MODEL = ?
                    WHERE ID = ?
                    """;

        } else {
            saveQuery = """
                    INSERT INTO CARS (COLOUR, BRAND, MODEL) 
                    VALUES (?, ?, ?)
                    """;
        }

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(saveQuery);
            queryStatement.setString(1, car.colour());
            queryStatement.setString(2, car.brand());
            queryStatement.setString(3, car.model());
            if (car.id() != null){
                queryStatement.setLong(4,car.id());
            }
            int queryResult = queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Unexpected sql exception occurred");
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> findAll() {
        var cars = new ArrayList<Car>();
        String allCarsQuery = """
                SELECT ID, COLOUR, BRAND, MODEL
                FROM CARS;
                """;
        try {
            Statement queryStatement = dbConnection.createStatement();
            ResultSet queryResult = queryStatement.executeQuery(allCarsQuery);

            while (queryResult.next()) {
                Long id = queryResult.getLong(1);
                String colour = queryResult.getString(2);
                String brand = queryResult.getString(3);
                String model = queryResult.getString(4);

                Car carFromDb = new Car(id, colour, brand, model);
                cars.add(carFromDb);
            }
        } catch (SQLException e) {
            System.out.println("Unexpected sql exception occurred");
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public Car findById(Long id) {
        Car result = null;
        String carByIdQuery = """
                SELECT ID, COLOUR, BRAND, MODEL
                FROM CARS
                WHERE ID = ?
                """;
        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(carByIdQuery);
            queryStatement.setLong(1, id);
            ResultSet queryResult = queryStatement.executeQuery();

            if (queryResult.next()) {
                result = new Car(
                        queryResult.getLong("ID"),
                        queryResult.getString("COLOUR"),
                        queryResult.getString("BRAND"),
                        queryResult.getString("MODEL")
                );
            }

        } catch (
                SQLException e) {
            System.out.println("Unexpected sql exception occurred");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        String carByIdQuery = """
                DELETE
                FROM CARS
                WHERE ID = ?
                """;
        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(carByIdQuery);
            queryStatement.setLong(1, id);
            int queryResult = queryStatement.executeUpdate();
            System.out.println("Deleted " + queryResult + " records.");
        } catch (
                SQLException e) {
            System.out.println("Unexpected sql exception occurred");
            e.printStackTrace();
        }

    }
}

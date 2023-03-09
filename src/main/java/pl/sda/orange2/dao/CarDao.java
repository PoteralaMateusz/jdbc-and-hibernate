package pl.sda.orange2.dao;

import pl.sda.orange2.entity.Car;

import java.util.List;

public class CarDao implements DataAccess<Car,Long> {


    @Override
    public void save(Car entity) {

    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}

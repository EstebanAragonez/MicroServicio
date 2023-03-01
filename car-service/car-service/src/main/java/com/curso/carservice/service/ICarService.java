package com.curso.carservice.service;

import com.curso.carservice.entity.Car;

import java.util.List;

public interface ICarService {
    List<Car> getAll() throws Exception;
    Car getCarById(int id) throws Exception;
    Car save(Car car) throws Exception;
    List<Car>byUserId(int userId) throws Exception;
}

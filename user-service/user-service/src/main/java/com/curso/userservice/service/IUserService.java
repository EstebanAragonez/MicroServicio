package com.curso.userservice.service;

import com.curso.userservice.entity.User;
import com.curso.userservice.model.Bike;
import com.curso.userservice.model.Car;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<User> getAll() throws Exception;
    User getUserById(int id) throws Exception;
    User save(User user) throws Exception;
    List<Car> getCars(int userId ) throws Exception;
    List<Bike> getBikes (int userId ) throws Exception;
    Car saveCar (int userId, Car car) throws Exception;
    Bike saveBike(int userId, Bike bike) throws Exception;
    Map<String, Object> getUserAndVehicles(int userId);
}

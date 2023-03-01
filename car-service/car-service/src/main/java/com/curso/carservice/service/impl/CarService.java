package com.curso.carservice.service.impl;

import com.curso.carservice.entity.Car;
import com.curso.carservice.repository.CarRepository;
import com.curso.carservice.service.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CarService implements ICarService {


   private final CarRepository carRepository;



    @Override
    public List<Car> getAll() throws Exception {
        try {
            return carRepository.findAll();
        }catch (Exception e){
            throw new Exception("no se encontro el listado de carros"+e);
        }

    }

    @Override
    public Car getCarById(int id) throws Exception {
        try {
            return carRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new Exception("no se encontro el carro"+e);
        }
    }

    @Override
    public Car save(Car car) throws Exception {
        try {
            Car carNew = carRepository.save(car);
            return carNew;
        }catch (Exception e){
            throw new Exception("no se pudo guardar el carro"+e);
        }
    }

    @Override
    public List<Car> byUserId(int userId) throws Exception {
        try {
            return carRepository.findByUserId(userId);
        }catch (Exception e){
            throw new Exception("no se encontro el listado de carros"+e);
        }
    }
}

package com.curso.carservice.controller;


import com.curso.carservice.entity.Car;
import com.curso.carservice.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() throws Exception {
        List<Car> Cars = carService.getAll();
        if (Cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Cars);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id")int id) throws Exception {
        Car car = carService.getCarById(id);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }
    @PostMapping
    public ResponseEntity<Car> save (@RequestBody Car car) throws Exception {
        Car carNew = carService.save(car);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carNew);
    }
    @GetMapping("byuser/{userId}")
    public ResponseEntity<List<Car>> getAll(@PathVariable ("userId")int userId) throws Exception {
        List<Car> Cars = carService.byUserId(userId);
        if (Cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Cars);
    }
}

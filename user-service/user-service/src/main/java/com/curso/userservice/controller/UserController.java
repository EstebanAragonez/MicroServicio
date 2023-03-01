package com.curso.userservice.controller;

import com.curso.userservice.entity.User;
import com.curso.userservice.model.Bike;
import com.curso.userservice.model.Car;
import com.curso.userservice.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() throws Exception {
        List<User> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id")int id) throws Exception {
        User user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);

    }
    @PostMapping
    public ResponseEntity<User> save (@RequestBody User user) throws Exception {
        User userNew = userService.save(user);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userNew);

    }
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>>getCars(@PathVariable("userId")int userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>>getBikes(@PathVariable("userId")int userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable int userId, @RequestBody Car car ) throws Exception {
        Car carNew = userService.saveCar(userId,car);
        return ResponseEntity.ok(car);
    }
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) throws Exception {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bike);
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

}
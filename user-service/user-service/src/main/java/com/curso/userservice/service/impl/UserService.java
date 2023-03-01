package com.curso.userservice.service.impl;

import com.curso.userservice.entity.User;
import com.curso.userservice.feignclients.BikeFeignClient;
import com.curso.userservice.feignclients.CarFeingClient;
import com.curso.userservice.model.Bike;
import com.curso.userservice.model.Car;
import com.curso.userservice.repository.UserRepository;
import com.curso.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final CarFeingClient carFeingClient;

    private final BikeFeignClient bikeFeingClient;


    @Override
    public List<User> getAll() throws Exception {
        try {
            return userRepository.findAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public User getUserById(int id) throws Exception {
        try {
            return userRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public User save(User user) throws Exception {
        try {
            User userNew = userRepository.save(user);
            return userNew;
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public List<Car> getCars(int userId) throws Exception {
        try {
            List<Car>cars = restTemplate.getForObject
                    ("http://localhost:8002/car/byuser/"+ userId, List.class);
            return cars;
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public List<Bike> getBikes(int userId) throws Exception {
        try {
            List<Bike> bikes = restTemplate.getForObject
                    ("http://localhost:8003/bike/byuser/"+ userId, List.class);
            return bikes;
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public Car saveCar(int userId, Car car) throws Exception {
        try {
            car.setUserId(userId);
            Car carNew = carFeingClient.save(car);
            return carNew;
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public Bike saveBike(int userId, Bike bike) throws Exception {
        try {
            bike.setUserId(userId);
            Bike bikeNew = bikeFeingClient.save(bike);
            return bikeNew;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Map<String, Object> getUserAndVehicles(int userId) {
        try {
            Map<String, Object> result = new HashMap<>();
            User user = userRepository.findById(userId).orElse(null);
            if(user == null) {
                result.put("Mensaje", "no existe el usuario");
                return result;
            }
            result.put("User", user);
            List<Car> cars = carFeingClient.getCars(userId);
            if(cars.isEmpty())
                result.put("Cars", "ese user no tiene coches");
            else
                result.put("Cars", cars);
            List<Bike> bikes = bikeFeingClient.getBikes(userId);
            if(bikes.isEmpty())
                result.put("Bikes", "ese user no tiene motos");
            else
                result.put("Bikes", bikes);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}

package com.curso.bikeservice.controller;


import com.curso.bikeservice.entity.Bike;
import com.curso.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> Bikes = bikeService.getAll();
        if (Bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Bikes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id")int id) {
        Bike bike = bikeService.getCarById(id);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }
    @PostMapping
    public ResponseEntity<Bike> save (@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeNew);
    }
    @GetMapping("byuser/{userId}")
    public ResponseEntity<List<Bike>> getAll(@PathVariable ("userId")int userId) {
        List<Bike> Bikes = bikeService.byUserId(userId);
        if (Bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Bikes);
    }
}

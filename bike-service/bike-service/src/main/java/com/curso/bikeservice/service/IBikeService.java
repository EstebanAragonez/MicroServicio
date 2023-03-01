package com.curso.bikeservice.service;

import com.curso.bikeservice.entity.Bike;

import java.util.List;

public interface IBikeService {
    List<Bike> getAll() throws Exception;
    Bike getBikeById(int id) throws Exception;
    Bike save(Bike bike) throws Exception;
    List<Bike>byUserId(int userId) throws Exception;
}

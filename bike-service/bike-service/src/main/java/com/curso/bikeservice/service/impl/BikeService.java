package com.curso.bikeservice.service.impl;

import com.curso.bikeservice.entity.Bike;
import com.curso.bikeservice.repository.BikeRepository;
import com.curso.bikeservice.service.IBikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BikeService implements IBikeService {


    private final BikeRepository bikeRepository;


    @Override
    public List<Bike> getAll() throws Exception {
        try{
            return bikeRepository.findAll();
        }catch (Exception e){
            throw new Exception("No se pudo obtener los bikes"+ e );
        }
    }
    @Override
    public Bike getBikeById(int id) throws Exception {
        try {
            return bikeRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new Exception("No se pudo obtener la bike"+ e );
        }
    }
    @Override
    public Bike save(Bike bike) throws Exception {
        try {
            Bike bikeNew = bikeRepository.save(bike);
            return bikeNew;
        }catch (Exception e){
            throw new Exception("No se pudo guardar la bike"+ e );
        }
    }

    @Override
    public List<Bike> byUserId(int userId) throws Exception {
        try {
            return bikeRepository.findByUserId(userId);
        }catch (Exception e){
            throw new Exception("No se pudo obtener los datos"+ e );
        }
    }
}

package com.ob.ejercicio3.service;

import com.ob.ejercicio3.entity.Laptop;

import java.util.List;
import java.util.Optional;

public interface ILaptopService {
    List<Laptop> findAll();
    Optional<Laptop> findOneById(Long id);
    Boolean create(Laptop laptop);
    Boolean update(Laptop laptop);
    Boolean deleteOneById(Long id);
    Boolean deleteAll();
}

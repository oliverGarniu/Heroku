package com.ob.laptop.service;

import com.ob.laptop.entity.Laptop;

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

package com.ob.ejercicio3.service;

import com.ob.ejercicio3.entity.Laptop;
import com.ob.ejercicio3.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService implements ILaptopService {

    private final Logger log = LoggerFactory.getLogger(LaptopService.class);

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public List<Laptop> findAll() {
        log.info("REST Request for finding all devices");
        if (laptopRepository.findAll().isEmpty()) {
            log.warn("No devices found");
        }
        return laptopRepository.findAll();
    }

    @Override
    public Optional<Laptop> findOneById(Long id) {
        log.info("REST Request for finding a laptop by id: {}", id);
        if (!laptopRepository.existsById(id)) {
            log.warn("There is no device with id: {}", id);
        }
        return laptopRepository.findById(id);
    }

    @Override
    public Boolean create(Laptop laptop) {
        log.info("REST Request for creating a laptop");
        boolean existingLaptop;
        if (laptop.getId() != null) {
            log.warn("Trying to create a laptop with id");
            return true;
        } else {
            laptopRepository.save(laptop);
            return false;
        }
    }

    @Override
    public Boolean update(Laptop laptop) {
        log.info("REST Request for updating a laptop");
        boolean existingLaptop;
        if (laptopRepository.existsById(laptop.getId())) {
            laptopRepository.save(laptop);
            return true;
        } else {
            log.warn("Trying to update a non existing laptop");
            return false;
        }
    }

    @Override
    public Boolean deleteOneById(Long id) {
        log.info("REST Request for deletion one laptop by id");
        boolean found = false;
        if (laptopRepository.existsById(id)) {
            laptopRepository.deleteById(id);
            System.out.println("Laptop with id: " + id + " was deleted succesfully.");
            found = true;
        } else {
            log.warn("There is no device with id: {}", id);
        }
        return found;
    }

    @Override
    public Boolean deleteAll() {
        log.info("REST Request for deletion all devices");
        boolean empty;
        if (laptopRepository.findAll().isEmpty()) {
            log.warn("There is no laptops for deletion");
            return true;
        } else {
            laptopRepository.deleteAll();
            System.out.println("All laptops were deleted successfully.");
            return false;
        }
    }
}

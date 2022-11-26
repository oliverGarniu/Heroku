package com.ob.ejercicio3.controller;

import com.ob.ejercicio3.entity.Laptop;
import com.ob.ejercicio3.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping("/laptops")
    public ResponseEntity<List<Laptop>> findAll() {
        List<Laptop> laptops = laptopService.findAll();
        return !laptops.isEmpty() ? ResponseEntity.ok(laptops) : ResponseEntity.notFound().build();
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<Optional<Laptop>> findOneById(@PathVariable Long id) {
        Optional<Laptop> laptop = laptopService.findOneById(id);
        return laptop.isPresent() ? ResponseEntity.ok(laptop) : ResponseEntity.notFound().build();
    }

    @PostMapping("/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        Boolean existingLaptop = laptopService.create(laptop);
        return existingLaptop ? ResponseEntity.badRequest().build() : ResponseEntity.ok(laptop);
    }

    @PutMapping("/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        Boolean existingLaptop = laptopService.update(laptop);
        return existingLaptop ? ResponseEntity.ok(laptop) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> deleteOneById(@PathVariable Long id) {
        Boolean found = laptopService.deleteOneById(id);
        return found ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<Laptop> deleteAll() {
        Boolean empty = laptopService.deleteAll();
        return !empty ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

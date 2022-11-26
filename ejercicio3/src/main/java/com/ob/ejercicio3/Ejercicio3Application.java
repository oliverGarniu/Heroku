package com.ob.ejercicio3;

import com.ob.ejercicio3.entity.Laptop;
import com.ob.ejercicio3.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Ejercicio3Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(com.ob.ejercicio3.Ejercicio3Application.class, args);
        LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

        Laptop laptop1 = new Laptop(null, "Apple", 1999.99, LocalDate.of(2021, 12, 15));
        Laptop laptop2 = new Laptop(null, "Dell", 1399.99, LocalDate.of(2020, 1, 15));
        Laptop laptop3 = new Laptop(null, "HP", 1199.99, LocalDate.of(2019, 7, 15));

        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);
        laptopRepository.save(laptop3);
    }
}

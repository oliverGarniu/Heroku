package com.ob.laptop.controller;

import com.ob.laptop.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Al ejecutar LaptopControllerTest primero se ejecuta create(), que crea un objeto
//que servirá para comprobar el resto de métodos del testing.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Test findAll method from controller Spring REST")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertEquals(1, laptops.size());
    }

    @DisplayName("Test findOneById method from controller Spring REST")
    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/2", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test create method from controller Spring REST")
    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "manufacturer": "Apple",
                    "price": 1999.99,
                    "release": "2020-12-20"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("Apple", result.getManufacturer());
        assertEquals(1999.99, result.getPrice());
    }
}
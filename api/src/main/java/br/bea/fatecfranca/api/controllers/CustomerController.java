package br.bea.fatecfranca.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bea.fatecfranca.api.entities.Customer;
import br.bea.fatecfranca.api.repositories.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer savedCustomer = repository.save(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCustomer);
    }


}


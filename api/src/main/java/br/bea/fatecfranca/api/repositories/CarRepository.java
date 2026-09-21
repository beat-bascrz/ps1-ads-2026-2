package br.bea.fatecfranca.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bea.fatecfranca.api.entities.Car;

public interface CarRepository

        extends JpaRepository<Car, Long> {

}


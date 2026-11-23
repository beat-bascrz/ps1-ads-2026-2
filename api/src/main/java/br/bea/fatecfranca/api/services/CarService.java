
package br.bea.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.bea.fatecfranca.api.dtos.CarRequest;
import br.bea.fatecfranca.api.entities.Car;
import br.bea.fatecfranca.api.entities.Customer;
import br.bea.fatecfranca.api.repositories.CarRepository;
import br.bea.fatecfranca.api.repositories.CustomerRepository;

import br.bea.fatecfranca.api.validators.RequestValidator;
import br.bea.fatecfranca.api.validators.ManufactureYearValidator;
import br.bea.fatecfranca.api.validators.SaleDataValidator;
import br.bea.fatecfranca.api.validators.UniquePlatesValidator;

@Service
public class CarService {

    private final CarRepository repository;
    private final CustomerRepository customerRepository;
    private final RequestValidator<CarRequest> validator;

    private void copyToEntity(
            CarRequest request,
            Car car) {

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());

        car.setYearManufacture(
                request.yearManufacture() == null
                        ? null
                        : Math.toIntExact(request.yearManufacture())
        );

        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());

        if (request.customerId() != null) {
            Customer customer = customerRepository.findById(request.customerId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Cliente não encontrado: " + request.customerId()));

            car.setCustomer(customer);
        } else {
            car.setCustomer(null);
        }
    }

    public CarService(
            CarRepository repository,
            CustomerRepository customerRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;

        RequestValidator<CarRequest> yearValidator =
                new ManufactureYearValidator(null);

        RequestValidator<CarRequest> saleValidator =
                new SaleDataValidator(yearValidator);

        this.validator = new UniquePlatesValidator(repository, saleValidator);
    }

    public Car create(CarRequest request) {

        validator.validate(request, null);

        Car car = new Car();

        copyToEntity(request, car);

        return repository.save(car);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Car update(Long id, CarRequest request) {

        validator.validate(request, id);

        Car car = new Car();

        copyToEntity(request, car);

        car.setId(id);

        return repository.save(car);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}


package br.bea.fatecfranca.api.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.bea.fatecfranca.api.dtos.CarRequest;

public class SaleDataValidator extends RequestValidator<CarRequest> {

    public SaleDataValidator(RequestValidator<CarRequest> next) {
        super(next);
    }

    @Override
    protected void check(CarRequest request, Long carId) {

        boolean dateMissing = request.sellingDate() == null;
        boolean priceMissing = request.sellingPrice() == null;

        if (dateMissing != priceMissing) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A data e o preço de venda devem ser preenchidos juntos."
            );
        }

        if (!priceMissing && request.sellingPrice().signum() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O preço de venda deve ser maior que zero."
            );
        }
    }
}

package br.bea.fatecfranca.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bea.fatecfranca.api.entities.Customer;

public interface CustomerRepository
      extends JpaRepository<Customer, Long> {

      boolean existsByEmail(String email);
      boolean existsByEmailAndIdNot(String email, Long id);


      boolean existsByIdentDocument(String identDocument);
      boolean existsByIdentDocumentAndIdNot(String identDocument, Long id);

}



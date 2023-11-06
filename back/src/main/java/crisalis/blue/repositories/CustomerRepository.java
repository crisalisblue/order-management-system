package crisalis.blue.repositories;

import crisalis.blue.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    void deleteById(Integer id);
    List<Customer> findAll();
    Optional<Customer> findByDni(String dni);
}

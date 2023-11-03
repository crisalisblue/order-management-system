package crisalis.blue.repositories;

import crisalis.blue.models.Customer;
import crisalis.blue.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

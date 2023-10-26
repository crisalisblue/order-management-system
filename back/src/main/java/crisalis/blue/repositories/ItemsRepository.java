package crisalis.blue.repositories;

import crisalis.blue.models.ExchangeGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends JpaRepository<ExchangeGood,Long> {
}

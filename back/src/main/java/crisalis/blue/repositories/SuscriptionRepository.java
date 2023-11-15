package crisalis.blue.repositories;

import crisalis.blue.models.Suscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long> {
}

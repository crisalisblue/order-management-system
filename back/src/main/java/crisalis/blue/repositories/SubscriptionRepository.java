package crisalis.blue.repositories;

import crisalis.blue.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
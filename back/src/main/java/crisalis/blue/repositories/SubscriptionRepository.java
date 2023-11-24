package crisalis.blue.repositories;

import crisalis.blue.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Boolean existsByCustomerId(@Param("customer_id") Long id);
    Boolean existsByAssetId(@Param("asset_id") Long id);
    Subscription findByCustomerId(@Param("customer_id") Long id);
    Subscription findByAssetId(@Param("asset_id") Long id);
}

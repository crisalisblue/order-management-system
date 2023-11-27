package crisalis.blue.repositories;

import crisalis.blue.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Boolean existsByCustomerId(@Param("customer_id") Long id);
    Boolean existsByAssetId(@Param("asset_id") Long id);
    List<Subscription> findAllByCustomerId(@Param("customer_id") Long id);
    List<Subscription> findAllByAssetId(@Param("asset_id") Long id);
}

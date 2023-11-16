package crisalis.blue.repositories;

import crisalis.blue.models.CalculatedTax;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatedTaxRepository extends JpaRepository<CalculatedTax, Long> {
}

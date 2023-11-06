package crisalis.blue.models;

import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tax")
@NoArgsConstructor
@AllArgsConstructor
public class Tax {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name", unique = true, nullable = false)
        private String name;

        @Column(name = "percentage")
        private BigDecimal percentage;

        // Montofijo
        @Column(name = "fixedAmount")
        private BigDecimal fixedAmount;

        public TaxDTO toDTO() {
                return TaxDTO
                                .builder()
                                .id(this.id)
                                .name(this.name)
                                .percentage(this.percentage)
                                .fixedAmount(this.fixedAmount)
                                .build();
        }

        // Falta agregar relacion con otras tablas...
}

package crisalis.blue.models;

import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

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

    @Column(
            name = "name",
            nullable = false)
    private String name;

    @Column(name = "percentage")
    private BigInteger percentage;

    //Montofijo
    @Column(name = "fixedAmount")
    private BigInteger fixedAmount;

    public TaxDTO ToDTO(){
        return
                TaxDTO
                        .builder()
                        .id(this.id)
                        .name(this.name)
                        .percentage(this.percentage)
                        .fixedAmount(this.fixedAmount)
                        .build();
    }

    //Falta agregar relacion con otras tablas...
}

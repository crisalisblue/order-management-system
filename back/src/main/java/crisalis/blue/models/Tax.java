package crisalis.blue.models;

import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "tax")
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "baseAmount")
    private BigInteger baseAmount;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @Column(name ="taxCalculated")
    private List<CalculatedTax> calculatedTax;
    @ManyToMany(mappedBy = "taxList")
    private List<Asset> assets;
    public TaxDTO toDTO(){
        return
                TaxDTO
                        .builder()
                        .id(this.id)
                        .name(this.name)
                        .percentage(this.percentage)
                        .baseAmount(this.baseAmount)
                        .assetList(this.assets.stream().map(Asset::getId).collect(Collectors.toList()))
                        .ordersList(this.calculatedTax.stream().map(CalculatedTax::getId).collect(Collectors.toList()))
                        .build();
    }

    //Falta agregar relacion con otras tablas...
}

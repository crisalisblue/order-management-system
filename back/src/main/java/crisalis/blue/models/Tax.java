package crisalis.blue.models;

import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal percentage;

    //Montofijo
    @Column(name = "baseAmount")
    private BigDecimal baseAmount;
    @OneToMany(mappedBy = "idTax")
    private List<CalculatedTax> calculatedTax;
    @ManyToMany(mappedBy = "taxList")
    private List<Asset> assets;
    public TaxDTO toDTO(){
        TaxDTO taxDTO = new TaxDTO();
        if(this.getId() != 0)
            taxDTO.setId(this.getId());
        if(this.getName()!= null && !this.getName().isEmpty())
            taxDTO.setName(this.getName());
        if(this.getBaseAmount()!=null && this.getBaseAmount().intValue()!=0)
            taxDTO.setBaseAmount(this.getBaseAmount());
        if(this.getPercentage()!=null && this.getPercentage().intValue() !=0)
            taxDTO.setPercentage(this.getPercentage());
        if(this.getAssets() != null && !this.getAssets().isEmpty())
        {
            for(int j=0; j<this.getAssets().size();j++)
            {
                if(this.getAssets().get(j)!=null)
                {
                    taxDTO.getAssetList().add(this.getAssets().get(j).getId());
                }
            }
        }
        if(this.getCalculatedTax() !=null && !this.getCalculatedTax().isEmpty())
        {
            for(int j=0; j<this.getCalculatedTax().size(); j++)
            {
                if(this.getCalculatedTax().get(j)!=null)
                {
                    taxDTO.getCalculatedTaxes().add(this.getCalculatedTax().get(j).getId());
                }
            }
        }
        return taxDTO;
    }

    //Falta agregar relacion con otras tablas...
}

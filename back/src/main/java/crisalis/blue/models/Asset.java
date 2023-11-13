package crisalis.blue.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.AssetDTO;
import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "asset")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@DiscriminatorColumn( name="type",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    private Long id ;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "baseAmount")
    private BigDecimal baseAmount;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="Tax_Asset",
    joinColumns = @JoinColumn(name = "id_asset",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_tax",referencedColumnName = "id"))
    @JsonProperty(value = "taxList")
    private List<Tax> taxList;



    public AssetDTO toAssetDTO()
    {
        AssetDTO assetDTO = new AssetDTO();
        if(this.getId() != null)
            assetDTO.setId(this.getId());
        if(this.getName()!=null && !this.getName().isEmpty())
            assetDTO.setName(this.getName());
        if(this.getBaseAmount()!=null && this.getBaseAmount().intValue() != 0)
            assetDTO.setBaseAmount(this.getBaseAmount());
        if(this instanceof Service service) {
            assetDTO.setSupportFree(service.getSupportFree());
        }
        if(this.getTaxList() != null && !this.getTaxList().isEmpty())
            assetDTO.setTaxDTOList((this.listTaxToTaxDTO(this.getTaxList())));
        if(this instanceof  Product)
            assetDTO.setType("Product");
        else
            assetDTO.setType("Service");
        return assetDTO;
    }
    public List<TaxDTO>listTaxToTaxDTO(List<Tax> listAsset)
    {
        return listAsset.stream().map(Tax::toDTO).collect(Collectors.toList());
    }
}

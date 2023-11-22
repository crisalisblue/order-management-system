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
    @JoinTable(name="Asset_Tax",
    joinColumns = @JoinColumn(name = "id_asset",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_tax",referencedColumnName = "id"))
    @JsonProperty(value = "taxList")
    private List<Tax> taxList;



    public AssetDTO toAssetDTO()
    {
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setId(this.getId());
        assetDTO.setName(this.getName());
        assetDTO.setBaseAmount(this.getBaseAmount());
        assetDTO.setTaxDTOList((this.listTaxToTaxDTO(this.getTaxList())));
        if(this instanceof  Product)
            assetDTO.setType("Product");
        else {
            assetDTO.setType("Service");
            Servicie service = (Servicie) this;
            assetDTO.setSupportFee(service.getSupportFee());
        }
        return assetDTO;
    }

    public List<TaxDTO>listTaxToTaxDTO(List<Tax> listAsset)
    {
        return listAsset.stream().map(Tax::toDTO).collect(Collectors.toList());
    }
}

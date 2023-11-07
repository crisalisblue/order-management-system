package crisalis.blue.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.AssetDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "asset")
@Inheritance(strategy = InheritanceType.JOINED)
@Data

public  class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    @Column(name ="id")
    private Long id ;
    @JsonProperty(value = "name")
    @Column(name = "name")
    private String name;
    @JsonProperty(value = "baseAmount")
    @Column(name = "baseAmount")
    private BigDecimal baseAmount;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "asset_tax",
            joinColumns = @JoinColumn(name="id_item",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tax_id",referencedColumnName = "id")
    )
    private List<Tax> taxList;
    public boolean checkEmpty()
    {
        if(!this.getName().equals(""))
        {
            if(this.getBaseAmount().intValue() !=0)
            {
                return true;
            }
        }
        return false;
    }

    public AssetDTO toAssetDTO()
    {
        AssetDTO assetDTO = new AssetDTO();
        if(this.getId() != null)
            assetDTO.setId(this.getId());
        if(this.getName()!=null && !this.getName().isEmpty())
            assetDTO.setName(this.getName());
        if(this.getBaseAmount()!=null && this.getBaseAmount().intValue() != 0)
            assetDTO.setBaseAmount(this.getBaseAmount());
        if(this.getTaxList() != null)
        {
            if(!this.getTaxList().isEmpty())
            {
                for(int j=0; j<this.getTaxList().size();j++)
                {
                    assetDTO.getTaxDTOList().add(this.getTaxList().get(j).getId());
                }
            }
        }
        return assetDTO;
    }
}

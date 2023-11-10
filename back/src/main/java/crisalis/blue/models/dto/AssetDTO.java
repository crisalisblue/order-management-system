package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Product;
import crisalis.blue.models.Service;
import crisalis.blue.models.Tax;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor

public class AssetDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("baseAmount")
    private BigDecimal baseAmount;
    @JsonProperty("taxList")
    private List<TaxDTO> taxDTOList;
    @JsonProperty("type")
    private String type;
    @JsonProperty("supportFree")
    private BigDecimal supportFree;
    public AssetDTO()
    {
        taxDTOList = new ArrayList<TaxDTO>();
    }
    public Asset assetDTOtoAsset()
    {
        if(this.getType().equals("Product"))
        {
            Product product = new Product();
            if(this.getId() != null);
                product.setId(this.getId());
            product.setName(this.getName());
            product.setType(this.getType());
            product.setBaseAmount(this.getBaseAmount());
            product.setTaxList(this.getTaxDTOList().stream().map(TaxDTO::toTax).collect(Collectors.toList()));
            return product;
        }else
        {
            Service service = new Service();
            if(this.getId() != null);
                service.setId(this.getId());
            service.setName(this.getName());
            service.setType(this.getType());
            service.setBaseAmount(this.getBaseAmount());
            service.setSupportFree(this.getSupportFree());
            service.setTaxList(this.getTaxDTOList().stream().map(TaxDTO::toTax).collect(Collectors.toList()));
            return service;
        }
    }


}
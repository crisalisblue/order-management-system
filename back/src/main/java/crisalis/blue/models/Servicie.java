package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.AssetDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "servicie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Servicie")
public class Servicie extends Asset {
    @JsonProperty("supportFee")
    @Column(name = "supportFee")
    private BigDecimal supportFee;

    public AssetDTO toAssetDTO() {
        AssetDTO assetDTO = super.toAssetDTO();
        assetDTO.setSupportFee(this.getSupportFee());
        return assetDTO;

    }

    public Servicie(AssetDTO assetDTO) {
        this.setId(assetDTO.getId());
        this.setName(assetDTO.getName());
        this.setBaseAmount(assetDTO.getBaseAmount());
        this.setSupportFee(assetDTO.getSupportFee());
    }
}
package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Service")
public class Service extends Asset {
    @JsonProperty("supportFee")
    @Column(name = "supportFee")
    private BigDecimal supportFee;

}
package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service extends Asset {

    @Column(name = "supportFree")
    private BigDecimal supportFree;



}
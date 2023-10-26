package crisalis.blue.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity(name = "Service")
public class Service extends ExchangeGood {
    @Id
    @JoinColumn(name = "id")
    private Long id;
    @JoinColumn(name = "support_charge")
    private double support_charge;
    @JoinColumn(name = "asset")
    private boolean asset;



}

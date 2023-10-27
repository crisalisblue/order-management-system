package crisalis.blue.models;

import crisalis.blue.models.dto.ServicioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio extends ExchangeGood {

    @Column(name = "support_charge")
    private double support_charge;
    @Column(name = "asset")
    private boolean asset;
    public ServicioDTO  servicieToDto()
    {
        return new ServicioDTO(this.support_charge,this.asset);
    }


}

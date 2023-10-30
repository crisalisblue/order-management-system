package crisalis.blue.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO extends ExchangeGoodDTO {
    private double support_charge;
    private boolean asset;


}

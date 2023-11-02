package crisalis.blue.models;
import crisalis.blue.models.dto.ServiceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service extends ExchangeGood {

    @Column(name = "support_charge")
    private double support_charge;
    @Column(name = "asset")
    private boolean asset;
    public ServiceDTO servicieToDto()
    {
        return new ServiceDTO(this.support_charge,this.asset);
    }


}
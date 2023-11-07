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
public class Service extends Asset {

    @Column(name = "supportFree")
    private double supportFree;
    public ServiceDTO servicieToDto()
    {
        return new ServiceDTO(this.supportFree);
    }


}
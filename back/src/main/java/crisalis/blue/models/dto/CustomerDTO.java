package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    //Atributos de Cliente
    @JsonProperty("id")
    private Integer id;


    //Atributos de Persona
    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dni")
    private String dni;


    //Atributos de Empresa

    //cuit
    @JsonProperty("businessName")
    private String businessName;

    @JsonProperty("activityStartDate")
    private Date activityStartDate;

    @JsonProperty("cuit")
    private String cuit;

    @JsonProperty("type")
    private String type;

}
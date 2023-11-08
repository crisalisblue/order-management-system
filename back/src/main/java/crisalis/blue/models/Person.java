package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Customer {

   public Person(CustomerDTO dto){
       super(dto);
       setName(dto.getName());
       setLastName(dto.getLastName());
       setDni(dto.getDni());
   }

    private String name;
    private String lastName;
    private String dni;


    @Override
    protected CustomerDTO completeSpecificAttrib(CustomerDTO dto) {
        dto.setType("PER");
        dto.setName(this.name);
        dto.setLastName(this.lastName);
        dto.setDni(this.dni);
        return dto;
    }
}

package crisalis.blue.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Customer {

    public Person(Long id, String address, String name, String lastName, String dni){
        super(id, address, "Person");
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
    }

    private String name;
    private String lastName;
    private String dni;
}

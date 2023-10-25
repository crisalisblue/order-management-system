package crisalis.blue.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="Item")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Items {
    @Id
    @JoinColumn(name ="id")
    private Long id ;
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "mountBase")
    private double mountBase;


}
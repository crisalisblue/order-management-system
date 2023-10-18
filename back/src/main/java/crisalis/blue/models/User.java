package crisalis.blue.models;

import crisalis.blue.models.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="Usuario")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "pass")
    private String password;

    @Column(name = "name")
    private String name;

    public User(UserDTO userDTO){
        this.name = userDTO.getName();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    public UserDTO toDTO(){
        return
                UserDTO
                        .builder()
                        .id(this.id)
                        .name(this.name)
                        .username(this.username)
                        .password(this.password)
                        .build();
    }

}

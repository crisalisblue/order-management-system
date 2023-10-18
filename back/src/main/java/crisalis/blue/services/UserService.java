package crisalis.blue.services;

import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.repositories.UserRepository;
import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.UnauthorizedException;
import crisalis.blue.models.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public  UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO){
        if ( checkUserDTO(userDTO, Boolean.FALSE) ){
            return this.userRepository.save(new User(userDTO));
        }
        throw new NotCreatedException("Error in save new User");
    }

    public UserDTO loginUserWithCredentials(String username, String password){
        if(
            this.checkUserDTO(
                    UserDTO
                        .builder()
                            .username(username)
                            .password(password)
                        .build()
                , Boolean.TRUE)
        ){
            return this.userRepository.findByUsernameAndPassword(username, password)
                    .orElseThrow(
                            ()-> new UnauthorizedException("Invalid credentials")
                    ).toDTO();
        }
        throw new UnauthorizedException("Invalid credentials");

    }

    public List<UserDTO> getListOfAllUsersInDB(){
        return this
                .userRepository
                .findAll()
                .stream()
                .map(User::toDTO)
                .collect(Collectors.toList());
    }

    private Boolean checkUserDTO(UserDTO userDTO, Boolean isForLogin){
        if (!isForLogin) {
            if (StringUtils.isEmpty(userDTO.getName())) {
                throw new EmptyElementException("Name is empty");
            }
        }
        if(StringUtils.isEmpty(userDTO.getUsername())){
            throw new EmptyElementException("Username is empty");
        }

        if(StringUtils.isEmpty(userDTO.getPassword())){
            throw new EmptyElementException("Password is empty");
        }

        return Boolean.TRUE;
    }

    public UserDTO getUserById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("ID Not Found") //Fijarse en ApiExceptionHandler, lo agregue en badRequest, no creo que sea correcto.
                ).toDTO();
    }

}

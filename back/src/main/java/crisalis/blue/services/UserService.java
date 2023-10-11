package crisalis.blue.services;

import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.repositories.UserRepository;
import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.UnauthorizedException;
import crisalis.blue.models.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public User updateUser(UserDTO userDTO)
    {
        Optional<User> aux=userRepository.findById(userDTO.getId());;
        if(aux != null){
            aux.get().setName(userDTO.getName());
            aux.get().setName(userDTO.getPassword());
            aux.get().setName(userDTO.getUsername());
            return userRepository.save(aux.get());
        }
        throw new EmptyElementException("Error");
    }
    /*
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
*/
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
    
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}

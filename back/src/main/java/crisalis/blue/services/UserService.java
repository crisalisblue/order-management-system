package crisalis.blue.services;

import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.jwt.JwtService;
import crisalis.blue.models.dto.JwtDTO;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.models.dto.UserDTOResponse;
import crisalis.blue.repositories.UserRepository;
import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.UnauthorizedException;
import crisalis.blue.models.User;
import crisalis.blue.validators.Encrypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static crisalis.blue.validators.Encrypt.encrypt;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserDTOResponse createUser(User user){
        if ( checkUser(user.toDTO(), Boolean.FALSE) ){
                return this.userRepository.save(new User(user)).toDTOResponse();
        }
        throw new NotCreatedException("Error 400 bad request.");
    }
    public UserDTOResponse updateUser(User user) throws Exception {
        Optional<User> aux=userRepository.findById(user.getId());
        if(aux.isPresent()){
            if(checkUser(user.toDTO(), Boolean.FALSE))
            {
                aux.get().setName(user.getName());
                aux.get().setPassword(Encrypt.encrypt(user.getPassword()));
                aux.get().setUsername(user.getUsername());
                return userRepository.save(aux.get()).toDTOResponse();
            }
        }
        throw new NotCreatedException("Error in save new User");
    }

    public JwtDTO loginUserWithCredentials(String username, String password) throws Exception {
        if (
                this.checkUser(
                        UserDTO
                                .builder()
                                .username(username)
                                .password(password)
                                .build()
                        , Boolean.TRUE)
        ) {
            UserDTO aux = this.userRepository.findByUsernameAndPassword(username, encrypt(password))
                    .orElseThrow(
                            () -> new UnauthorizedException("Invalid credentials")
                    ).toDTO();
               return JwtService.getToken(aux);
        }
        throw new UnauthorizedException("Invalid credentials");

    }


    public List<UserDTOResponse> getListOfAllUsersInDB(){
        return this
                .userRepository
                .findAll()
                .stream()
                .map(User::toDTOResponse)
                .collect(Collectors.toList());
    }

    private Boolean checkUser(UserDTO userDTO, Boolean isForLogin){
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

    public UserDTOResponse deleteUser(int id) {
        if (userRepository.existsById(id)) {
            Optional<User> aux = userRepository.findById(id);
            userRepository.deleteById(id);
            return aux.get().toDTOResponse();

        } else {
            throw new EmptyElementException("No existe un usuario con id " + id + ".");
        }
    }

    public UserDTO getUserById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("ID Not Found")
                ).toDTO();
    }

}

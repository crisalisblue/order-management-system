package crisalis.blue.services;

import crisalis.blue.jwt.JwtService;
import crisalis.blue.models.dto.UserDTO;
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

    public User saveUser(UserDTO userDTO) throws Exception {
        if (checkUserDTO(userDTO, Boolean.TRUE)) {
            User user = new User(
                    userDTO
                            .builder()
                            .name(userDTO.getName())
                            .username(userDTO.getUsername())
                            .password(Encrypt.encrypt(userDTO.getPassword()))
                            .build()
            );
            userRepository.save(user);
            return user;
        }
        throw new NotCreatedException("Error in save new User");
    }

    public String loginUserWithCredentials(String username, String password) throws Exception {
        if (
                this.checkUserDTO(
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



}
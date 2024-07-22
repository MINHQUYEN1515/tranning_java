package tranning.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.model.UserEntity;

public class UserRequestToUserEntity {
    @Autowired
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public static UserEntity parseToUserEntity(UserRequest user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_name(user.getUser_name());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setImage(user.getImage());
        userEntity.setCreated_at(LocalDateTime.now());
        userEntity.setUpdated_at(user.getUpdated_at());
        return userEntity;
    }
}

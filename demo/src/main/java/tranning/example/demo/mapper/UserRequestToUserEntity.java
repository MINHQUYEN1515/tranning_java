package tranning.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.model.UserEntity;
import tranning.example.demo.model.enums.RoleEnum;

public class UserRequestToUserEntity {
    @Autowired
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public static UserEntity parseToUserEntity(UserRequest user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_name(user.getUser_name());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(RoleEnum.USER.name());
        return userEntity;
    }
}

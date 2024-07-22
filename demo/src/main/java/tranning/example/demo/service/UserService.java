package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.mapper.UserRequestToUserEntity;
import tranning.example.demo.model.UserEntity;
import tranning.example.demo.reponsitories.UserRepositories;

@Service
public class UserService {
    @Autowired
    private UserRepositories userRepositories;

    private static UserRequestToUserEntity userMapper = new UserRequestToUserEntity();

    @Transactional
    public void signup(UserRequest user) {

        UserEntity userEntity = userMapper.parseToUserEntity(user);

        userRepositories.save(userEntity);
    }
}

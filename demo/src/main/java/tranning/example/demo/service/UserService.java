package tranning.example.demo.service;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.EditProfile;
import tranning.example.demo.dto.request.UpdateImage;
import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.mapper.UserRequestToUserEntity;
import tranning.example.demo.model.UserEntity;
import tranning.example.demo.reponsitories.UserRepositories;

@Service
public class UserService {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/demo/src/main/resources/static/image/";
    private final Path root = Paths.get(UPLOAD_DIRECTORY);
    @Autowired
    private UserRepositories userRepositories;

    private static UserRequestToUserEntity userMapper = new UserRequestToUserEntity();

    @Transactional
    public void signup(UserRequest user) {

        UserEntity userEntity = userMapper.parseToUserEntity(user);

        userRepositories.save(userEntity);
    }

    public UserEntity getProfile(String email) {
        return userRepositories.findByEmail(email);
    }

    @Transactional
    public UserEntity editProfile(EditProfile user) {
        UserEntity userEntity = userRepositories.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        userEntity.setUser_name(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userRepositories.save(userEntity);
        return userEntity;
    }

    public boolean updateImage(UpdateImage file) {
        if (file.getImage().isEmpty()) {

            return false;
        }

        UserEntity user = userRepositories.findById(file.getUser_id())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if (user.getImage() != null) {
            File file_delete = new File(UPLOAD_DIRECTORY + user.getImage());
            file_delete.delete();
        }

        String fileName = randomAlphaNumeric(10).concat("-" + file.getImage().getOriginalFilename());
        try {
            Files.copy(file.getImage().getInputStream(), this.root.resolve(fileName));
            user.setImage(fileName);
            userRepositories.save(user);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.print(e);
        }
        return true;
    }

    // =====================================================
    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}

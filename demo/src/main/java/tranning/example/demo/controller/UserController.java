package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

import io.github.bucket4j.Refill;
import jakarta.validation.Valid;

import tranning.example.demo.dto.request.AuthenticationRequest;
import tranning.example.demo.dto.request.EditProfile;
import tranning.example.demo.dto.request.UpdateImage;
import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.dto.response.LoginReponse;
import tranning.example.demo.model.UserEntity;
import tranning.example.demo.service.AuthenticationService;
import tranning.example.demo.service.UserService;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.MediaType;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    private final Bucket bucket;

    public UserController() {
        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid UserRequest entity) {
        try {
            userService.signup(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Create user success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "create faild", e.getMessage()));

        }

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest request) {
        if (bucket.tryConsume(1)) {
            String token = authenticationService.checkUser(request);
            if (token == null) {
                return ResponseEntity.badRequest().body(new ApiResponse(400, "email or password not found", null));

            }
            UserEntity user = userService.getProfile(request.getEmail());
            return ResponseEntity.ok().body(new LoginReponse(200, "Login success!",
                    user, token));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(
                new ApiResponse(429, HttpStatus.TOO_MANY_REQUESTS.toString(),
                        null));
    }

    @GetMapping("/test")
    public ResponseEntity test() {

        if (bucket.tryConsume(1)) {
            var authenticated = SecurityContextHolder.getContext().getAuthentication();
            return ResponseEntity.ok().body(new ApiResponse(200, "Login success!",
                    authenticated.getName()));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ApiResponse(429, HttpStatus.TOO_MANY_REQUESTS.toString(),
                        null));

    }

    // @GetMapping("/profile")
    // public ResponseEntity getProfile() {

    // }

    @GetMapping(value = "/image/{image}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getimage(@PathVariable("image") String image) throws IOException {
        return getClass().getResourceAsStream("/static/image/" + image).readAllBytes();
    }

    @PostMapping("/edit-profile")
    public ResponseEntity postMethodName(@RequestBody @Valid EditProfile entity) {
        try {
            UserEntity user = userService.editProfile(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Update user success!", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Update user faild!", e.getMessage()));

        }
    }

    @PostMapping(value = "/update-image", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postMethodName(@Valid UpdateImage file) {
        try {
            boolean user = userService.updateImage(file);
            return ResponseEntity.ok().body(new ApiResponse(200, "Update user success!", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Update user faild!", e.getMessage()));

        }
    }

}

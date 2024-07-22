package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.AuthenticationRequest;
import tranning.example.demo.dto.request.UserRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.AuthenticationService;
import tranning.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/v1/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

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
        String token = authenticationService.checkUser(request);
        if (token == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "email or password not found", null));

        }
        return ResponseEntity.ok().body(new ApiResponse(200, "Create user success!",
                token));
    }

    @PostMapping("/test")
    public String postMethodName(@RequestBody String entity) {

        return "Test";
    }

    // @GetMapping("/profile")
    // public ResponseEntity getProfile() {

    // }

}

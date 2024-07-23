package tranning.example.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;

import com.nimbusds.jwt.JWTClaimsSet;

import tranning.example.demo.dto.request.AuthenticationRequest;
import tranning.example.demo.reponsitories.UserRepositories;

@Component
public class AuthenticationService {
    @Autowired
    UserRepositories userRepositories;

    private final String SECRET_KEY = "Yn7MAHdm9Gi9QnTk6F5jCsQatUNJW8zp";
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String checkUser(AuthenticationRequest request) {
        var user = userRepositories.findUser(request.getEmail());
        boolean checkAuth = passwordEncoder.matches(request.getPassword(),
                user);
        if (!checkAuth) {
            return null;
        }
        String token = generateToken(request.getEmail());
        return token;
    }

    private String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("demo.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(5, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customerclaim", "customer")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}

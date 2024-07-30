package tranning.example.demo.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import tranning.example.demo.Config.AppConfig;
import tranning.example.demo.dto.request.AuthenticationRequest;
import tranning.example.demo.dto.request.ChangePasswordRequest;
import tranning.example.demo.dto.request.LogoutRequest;
import tranning.example.demo.exception.AppException;
import tranning.example.demo.exception.ErrorCode;
import tranning.example.demo.model.Logout;
import tranning.example.demo.model.UserEntity;
import tranning.example.demo.reponsitories.Logoutrepositories;
import tranning.example.demo.reponsitories.UserRepositories;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepositories userRepositories;
    @Autowired
    Logoutrepositories invalidatedTokenRepository;

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

    public boolean checkToken(String token) {
        try {
            verifyToken(token, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("demo.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(5, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customerclaim", "customer")
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(AppConfig.SECRET_KEY.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            Logout invalidatedToken = new Logout();
            invalidatedToken.setId(jit);
            invalidatedToken.setExpiration_date(expiryTime);
            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
            System.out.print(exception.getMessage());
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(AppConfig.SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                        .toInstant().plus(5, ChronoUnit.HOURS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    public void deleteRowsLogoutTable() {
        invalidatedTokenRepository.deleteRowsLogoutTable();
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        UserEntity user = userRepositories.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(request.getCurrent_password(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNew_password()));
        } else {
            throw new RuntimeException("Password not match! ");
        }
    }

    @Transactional
    public void deleteUser(Long user_id) {
        if (userRepositories.existsById(user_id)) {

            userRepositories.deleteById(user_id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}

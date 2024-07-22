package tranning.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotNull(message = "Trường email không được null")
    String email;
    @NotNull(message = "Trường password không được null")
    String password;
}

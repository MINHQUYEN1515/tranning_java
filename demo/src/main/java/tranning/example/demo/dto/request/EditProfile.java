package tranning.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditProfile {
    @NotNull(message = "Trường id không được để trống")
    Long id;
    @NotNull(message = "Trường username không được để trống")

    String username;
    @NotNull(message = "Trường email không được để trống")
    @Email
    String email;
}

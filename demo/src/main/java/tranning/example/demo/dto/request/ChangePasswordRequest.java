package tranning.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @NotNull(message = "Trường id không được để trống")

    Long id;
    @NotNull(message = "Trường current_password không được để trống")
    String current_password;
    @NotNull(message = "Trường new_password không được để trống")
    String new_password;
}

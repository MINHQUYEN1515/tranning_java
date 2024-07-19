package tranning.example.demo.dto.request;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserRequest {

    @NotNull(message = "Trường username không được để trống")
    String user_name;
    String image;
    @NotNull(message = "Trường email không được để trống")
    @Email(message = "Email nhập vào không hợp lệ")
    String email;
    @NotNull(message = "Trường password không được để trống")
    String password;
    Date created_at;
    Date updated_at;
}

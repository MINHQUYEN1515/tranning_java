package tranning.example.demo.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import tranning.example.demo.utils.FileValid;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateImage {
    @NotNull(message = "Trường image này không được trống")
    @FileValid
    MultipartFile image;
    @NotNull(message = "Trường user_id này không được trống")
    Long user_id;
}

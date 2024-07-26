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
public class YardItemRequest {
    @NotNull(message = "trường yard_id không được để trống")
    Long yard_id;
    @NotNull(message = "trường image không được để trống")
    @FileValid
    MultipartFile image;
}

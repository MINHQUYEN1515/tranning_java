package tranning.example.demo.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YardRequest {
    @NotNull(message = "Trường time_start không được để trống")
    LocalDateTime time_start;
    @NotNull(message = "Trường time_end không được để trống")
    LocalDateTime time_end;
    @NotNull(message = "Trường addres không được để trống")
    String Address;
    String name;
    String status;
    String image;
}

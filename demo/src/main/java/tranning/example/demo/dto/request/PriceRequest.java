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
public class PriceRequest {
    @NotNull(message = "Trường price_type không được bỏ trống")
    Integer price_type;
    @NotNull(message = "Trường price không được bỏ trống")
    Long price;
    @NotNull(message = "Trường time_start không được bỏ trống")

    LocalDateTime time_start;
    @NotNull(message = "Trường time_end không được bỏ trống")
    LocalDateTime time_end;

}

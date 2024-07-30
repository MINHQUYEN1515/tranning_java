package tranning.example.demo.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdatePrice {
    @NotNull(message = "Trường id không được bỏ trống")
    Long id;
    @NotNull(message = "Trường price_type không được bỏ trống")
    Integer price_type;
    @NotNull(message = "Trường price không được bỏ trống")
    Long price;
    @NotNull(message = "Trường time_start không được bỏ trống")

    LocalDateTime time_start;
    @NotNull(message = "Trường time_end không được bỏ trống")
    LocalDateTime time_end;
}

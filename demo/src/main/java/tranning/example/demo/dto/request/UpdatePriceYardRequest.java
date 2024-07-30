package tranning.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePriceYardRequest {
    @NotNull(message = "Trường yard_id không được bỏ trống")
    Long yard_id;
    @NotNull(message = "Trường price_id không được bỏ trống")

    Long price_id;
}

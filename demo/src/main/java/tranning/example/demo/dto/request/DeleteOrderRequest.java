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
public class DeleteOrderRequest {
    @NotNull(message = "Trường order_id không được để trống")
    Long order_id;
    @NotNull(message = "Trường order_detail_id không được để trống")
    Long order_detail_id;
}

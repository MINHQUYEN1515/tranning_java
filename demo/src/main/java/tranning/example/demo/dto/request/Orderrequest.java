package tranning.example.demo.dto.request;

import java.util.Date;

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
public class Orderrequest {
    @NotNull(message = "Trường yard_item_id không được để trống")
    Long yard_item_id;
    @NotNull(message = "Trường time_start không được để trống")

    Date time_start;
    @NotNull(message = "Trường time_end không được để trống")

    Date time_end;
    @NotNull(message = "Trường price không được để trống")

    Long price;
    @NotNull(message = "Trường phone không được để trống")

    String phone;
    @NotNull(message = "Trường user_id không được để trống")

    Long user_id;

}

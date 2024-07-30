package tranning.example.demo.dto.request;

import java.time.LocalTime;
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
    @NotNull(message = "Trường time_start không được để trống")
    LocalTime time_start;
    @NotNull(message = "Trường time_end không được để trống")

    LocalTime time_end;
    @NotNull(message = "Trường price không được để trống")

    Long price;
    @NotNull(message = "Trường phone không được để trống")

    String phone;
    @NotNull(message = "Trường name không được để trống")

    String name;
    @NotNull(message = "Trường name không được để trống")
    Long yard_id;

}

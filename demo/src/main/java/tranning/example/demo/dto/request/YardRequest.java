package tranning.example.demo.dto.request;

import java.util.Date;

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
    Date time_start;
    @NotNull(message = "Trường time_end không được để trống")
    Date time_end;
    @NotNull(message = "Trường count_yarn_item không được để trống")
    Integer count_yarn_item;
    @NotNull(message = "Trường address không được để trống")

    String address;
    @NotNull(message = "Trường price_morning không được để trống")

    Long price_morning;
    @NotNull(message = "Trường price_afternoon không được để trống")

    Long price_afternoon;
    @NotNull(message = "Trường price_evening không được để trống")

    Long price_evening;

}

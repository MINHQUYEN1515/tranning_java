package tranning.example.demo.dto.request;

import java.time.LocalDateTime;

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
public class ChangeTimeOrder {
    @NotNull(message = "Trường order_detail_id không được bỏ trống")
    Long order_detail_id;
    @NotNull(message = "Trường time_start không được bỏ trống")

    LocalDateTime time_start;
    @NotNull(message = "Trường time_end không được bỏ trống")

    LocalDateTime time_end;
    @NotNull(message = "Trường price không được bỏ trống")
    Long price;
    @NotNull(message = "Trường yard_id không được bỏ trống")
    Long yard_id;
    @NotNull(message = "Trường message_report không được phép rỗng")
    String message_report;
}

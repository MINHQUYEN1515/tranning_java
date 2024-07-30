package tranning.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeletePrice {
    @NotNull(message = "Trường id không được phép bỏ trống")
    Long id;
}

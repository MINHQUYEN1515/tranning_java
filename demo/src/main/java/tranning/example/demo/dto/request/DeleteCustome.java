package tranning.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteCustome {
    @NotNull(message = "Trường id không được bỏ trống")
    Long id;
}

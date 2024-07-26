package tranning.example.demo.dto.response;

import lombok.AccessLevel;
import lombok.Data;

import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemYardReponse {
    Long id;

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Long getYard_id() {
        return yard_id;
    }

    String image;
    Long yard_id;
}

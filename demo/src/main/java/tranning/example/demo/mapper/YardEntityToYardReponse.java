package tranning.example.demo.mapper;

import org.mapstruct.Mapper;

import tranning.example.demo.dto.response.YardReponse;
import tranning.example.demo.model.YardEntity;

@Mapper(componentModel = "spring")
public class YardEntityToYardReponse {
    public static YardReponse parseYardReponse(YardEntity yardEntity) {
        YardReponse yard = new YardReponse();
        yard.setId(yardEntity.getId());
        yard.setImage(yardEntity.getImage());
        yard.setTimeStart(yardEntity.getTimeStart());
        yard.setTimeEnd(yardEntity.getTimeEnd());
        yard.setAddress(yardEntity.getAddress());
        yard.setCreatedAt(yardEntity.getCreatedAt());
        yard.setUpdatedAt(yardEntity.getUpdatedAt());
        return yard;
    }
}

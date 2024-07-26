package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.YardItemRequest;
import tranning.example.demo.model.YardItemEntity;
import tranning.example.demo.model.enums.Status_Yard;

public class YardItemRequestToYardItem {
    public static YardItemEntity parseYardRequesttoYardEntity(YardItemRequest request) {
        YardItemEntity yard_item = new YardItemEntity();
        yard_item.setImage(request.getImage().getOriginalFilename());
        yard_item.setYard_id(request.getYard_id());
        yard_item.setStatus(Status_Yard.CHUA_DAT.name());
        return yard_item;
    }
}

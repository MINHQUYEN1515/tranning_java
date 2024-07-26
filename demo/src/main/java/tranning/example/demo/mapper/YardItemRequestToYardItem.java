package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.YardItemRequest;
import tranning.example.demo.model.YardItemEntity;

public class YardItemRequestToYardItem {
    public static YardItemEntity parseYardRequesttoYardEntity(YardItemRequest request) {
        YardItemEntity yard_item = new YardItemEntity();
        yard_item.setImage(request.getImage().getOriginalFilename());
        yard_item.setYard_id(request.getYard_id());
        return yard_item;
    }
}

package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.model.YardEntity;

public class YardRequestToYard {
    public static YardEntity parseToYard(YardRequest request) {
        YardEntity yard = new YardEntity();
        yard.setName(request.getName());
        yard.setTimeStart(request.getTime_start());
        yard.setTimeEnd(request.getTime_end());
        yard.setAddress(request.getAddress());
        yard.setStatus(request.getStatus());
        yard.setImage(request.getImage());
        return yard;

    }
}

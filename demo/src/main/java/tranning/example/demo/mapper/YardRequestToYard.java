package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.model.YardEntity;

public class YardRequestToYard {
    public static YardEntity parseToYard(YardRequest request) {
        YardEntity yard = new YardEntity();
        // yard.setTime_start(request.getTime_start());
        // yard.setTime_end(request.getTime_end());
        // yard.setAddress(request.getAddress());
        // yard.setCount_yarn_item(request.getCount_yarn_item());
        // yard.setPrice_morning(request.getPrice_morning());
        // yard.setPrice_afternoon(request.getPrice_afternoon());
        // yard.setPrice_evening(request.getPrice_afternoon());
        return yard;

    }
}

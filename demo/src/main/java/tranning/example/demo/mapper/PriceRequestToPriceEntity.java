package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.PriceRequest;
import tranning.example.demo.model.PriceEntity;

public class PriceRequestToPriceEntity {
    public static PriceEntity parse(PriceRequest request) {
        PriceEntity price = new PriceEntity();
        price.setPrice(request.getPrice());
        price.setTimeStart(request.getTime_start());
        price.setTimeEnd(request.getTime_end());
        price.setTypePrice(request.getPrice_type());
        price.setStatus(1);
        return price;
    }

}

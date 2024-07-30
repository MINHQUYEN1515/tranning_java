package tranning.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.PriceRequest;
import tranning.example.demo.dto.request.UpdatePriceYardRequest;
import tranning.example.demo.mapper.PriceRequestToPriceEntity;
import tranning.example.demo.model.PriceEntity;
import tranning.example.demo.reponsitories.PriceRepositories;

@Service
public class PriceService {
    @Autowired
    private PriceRepositories priceRepositories;

    private PriceRequestToPriceEntity mapper;

    @Transactional
    public void createPrice(PriceRequest priceRequest) {
        PriceEntity price = mapper.parse(priceRequest);
        priceRepositories.save(price);
    }

    public void addPriceToYard(UpdatePriceYardRequest request) {
        PriceEntity price = priceRepositories.findById(request.getPrice_id())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giá!"));
        price.setYardId(request.getYard_id());
        priceRepositories.save(price);
    }

    public List<PriceEntity> getAll() {
        return priceRepositories.findAll();
    }
}

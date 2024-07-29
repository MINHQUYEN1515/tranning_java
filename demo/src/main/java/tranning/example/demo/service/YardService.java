package tranning.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.dto.response.YardReponse;
import tranning.example.demo.mapper.YardEntityToYardReponse;
import tranning.example.demo.mapper.YardRequestToYard;

import tranning.example.demo.model.YardEntity;

import tranning.example.demo.reponsitories.YardRepositories;

@Service
public class YardService {
    @Autowired
    private YardRepositories yardRepositories;

    private YardRequestToYard mapper;

    private YardEntityToYardReponse mapYardReponse;

    public List<YardEntity> getAll() {
        try {

            return yardRepositories.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void craeteYard(YardRequest request) {
        try {
            YardEntity yard = mapper.parseToYard(request);
            yardRepositories.save(yard);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public YardReponse getById(Long id) {
        YardEntity yardEntity = yardRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Id Not found"));
        YardReponse yardReponse = mapYardReponse.parseYardReponse(yardEntity);
        return yardReponse;

    }
}

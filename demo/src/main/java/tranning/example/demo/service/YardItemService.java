package tranning.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tranning.example.demo.dto.request.YardItemRequest;
import tranning.example.demo.mapper.YardItemRequestToYardItem;
import tranning.example.demo.model.YardItemEntity;
import tranning.example.demo.reponsitories.YardItemRepositories;

@Service
public class YardItemService {
    @Autowired
    private YardItemRepositories yardItemRepositories;

    private YardItemRequestToYardItem mapper;
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/demo/src/main/resources/static/yard/";
    private final Path root = Paths.get(UPLOAD_DIRECTORY);

    public void createYardItem(YardItemRequest request) {
        try {

            YardItemEntity yardItemEntity = mapper.parseYardRequesttoYardEntity(request);
            String fileName = UUID.randomUUID().toString().concat("-" + request.getImage().getOriginalFilename());
            yardItemEntity.setImage(fileName);
            Files.copy(request.getImage().getInputStream(), this.root.resolve(fileName));
            yardItemRepositories.save(yardItemEntity);
        } catch (Exception e) {
            throw new RuntimeException("Cannot save yard_item");
        }

    }

    public List<YardItemEntity> getAll() {
        return yardItemRepositories.findAll();
    }
}

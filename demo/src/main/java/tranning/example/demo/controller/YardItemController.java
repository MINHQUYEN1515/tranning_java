package tranning.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.YardItemRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.YardItemService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "api/v1/yard-item")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class YardItemController {
    @Autowired
    private YardItemService yardItemService;

    @PostMapping(path = "/create-yard-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postMethodName(@Valid YardItemRequest entity) {
        try {
            yardItemService.createYardItem(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Create Yard Item success!", null));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ApiResponse(400, "Create Yard Item faild!", e.getMessage()));

        }

    }

    @GetMapping("/getAll")
    public ResponseEntity getMethodName() {
        return ResponseEntity.ok().body(new ApiResponse(200, "Get  Yard Item success!", yardItemService.getAll()));

    }

}

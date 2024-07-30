package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.PriceRequest;
import tranning.example.demo.dto.request.UpdatePriceYardRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/v1/price")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/create-price")
    public ResponseEntity postMethodName(@RequestBody @Valid PriceRequest entity) {

        try {
            priceService.createPrice(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Create price success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Create price faild", e.getMessage()));

        }
    }

    @PostMapping("/update-to-yard")
    public ResponseEntity postMethodName(@RequestBody @Valid UpdatePriceYardRequest entity) {

        try {
            priceService.addPriceToYard(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Add price success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Add price faild", e.getMessage()));

        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getMethodName() {
        return ResponseEntity.ok().body(new ApiResponse(200, "Get all price success!", priceService.getAll()));

    }

}

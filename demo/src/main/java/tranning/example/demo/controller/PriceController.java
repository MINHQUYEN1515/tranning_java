package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.DeletePrice;
import tranning.example.demo.dto.request.PriceRequest;
import tranning.example.demo.dto.request.UpdatePrice;
import tranning.example.demo.dto.request.UpdatePriceYardRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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

    @PostMapping("/update")
    public ResponseEntity updatePrice(@RequestBody @Valid UpdatePrice entity) {
        try {
            priceService.updatePrice(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Delete price success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Delete price faild", e.getMessage()));

        }
    }

    @PostMapping("/delete")
    public ResponseEntity postMethodName(@RequestBody @Valid DeletePrice request) {
        try {
            priceService.deletePrice(request.getId());
            return ResponseEntity.ok().body(new ApiResponse(200, "Update price success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Update price faild", e.getMessage()));

        }
    }

}

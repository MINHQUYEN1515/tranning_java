package tranning.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.YardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/yard")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class YardController {
    @Autowired
    private YardService yardService;

    @SuppressWarnings("")
    @GetMapping("/getAll")
    public ResponseEntity signup() {

        return ResponseEntity.ok().body(new ApiResponse(200, "Get All Yard success!", yardService.getAll()));

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getMethodName(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(new ApiResponse(200, "Get All Yard success!", yardService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ApiResponse(500, "Get Yard faild!", e.getMessage()));

        }

    }

    @PostMapping("/create-yard")
    public ResponseEntity postMethodName(@RequestBody @Valid YardRequest entity) {
        try {
            yardService.craeteYard(entity);
            return ResponseEntity.ok().body(new ApiResponse(200, "Create Yard success!", yardService.getAll()));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ApiResponse(400, "Create Yard faild!", e.getMessage()));

        }

    }

    @GetMapping(value = "/image/{image}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getimage(@PathVariable("image") String image) throws IOException {
        return getClass().getResourceAsStream("/static/yard/" + image).readAllBytes();
    }

}

package tranning.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity signup(@RequestParam("date") LocalDateTime date) {

        return ResponseEntity.ok().body(new ApiResponse(200, "Get All Yard success!", yardService.getAll(date)));

    }

    @GetMapping(value = "/image/{image}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getimage(@PathVariable("image") String image) throws IOException {
        return getClass().getResourceAsStream("/static/yard/" + image).readAllBytes();
    }

}

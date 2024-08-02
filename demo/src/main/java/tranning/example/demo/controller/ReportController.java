package tranning.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/report")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/get-report")
    public ResponseEntity getMethodName(@RequestParam(name = "type", required = false) String param) {
        if (param == null) {

            return ResponseEntity.ok().body(new ApiResponse(200, "Get all report success!", reportService.getAll()));
        } else {
            return ResponseEntity.ok()
                    .body(new ApiResponse(200, "Get all report success!", reportService.getAllFilter(param)));

        }
    }

}

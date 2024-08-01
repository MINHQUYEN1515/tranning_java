package tranning.example.demo.service;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.UpdateYardImage;
import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.dto.response.OrderReponse;
import tranning.example.demo.dto.response.YardReponse;
import tranning.example.demo.mapper.OrderParseOrderReponse;
import tranning.example.demo.mapper.YardEntityToYardReponse;
import tranning.example.demo.mapper.YardRequestToYard;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.OrdersDetail;
import tranning.example.demo.model.PriceEntity;
import tranning.example.demo.model.YardEntity;
import tranning.example.demo.reponsitories.PriceRepositories;
import tranning.example.demo.reponsitories.YardRepositories;
import tranning.example.demo.reponsitories.OrderDetailRepositories;
import tranning.example.demo.reponsitories.OrderReposotories;

@Service
public class YardService {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/demo/src/main/resources/static/yard/";
    private final Path root = Paths.get(UPLOAD_DIRECTORY);
    
    @Autowired
    private YardRepositories yardRepositories;
    @Autowired
    private PriceRepositories priceRepositories;
    @Autowired
    private OrderDetailRepositories orderDetailRepositories;
    @Autowired
    private OrderReposotories orderReposotories;

    private YardRequestToYard mapper;

    private OrderParseOrderReponse orderParseOrderReponse;

    public List<YardReponse> getAll(LocalDate date) {
        LocalDate date_query;
        if (date == null) {
            date_query = LocalDate.now();

        }
        date_query = date;
        try {

            List<YardEntity> yard = yardRepositories.findAll();
            List<YardReponse> yardReponses = new ArrayList<YardReponse>();
            for (Integer i = 0; i < yard.size(); i++) {// duyệt qua các sân
                List<OrderReponse> orderReponses = new ArrayList<OrderReponse>();
                YardReponse temp = YardEntityToYardReponse.parseYardReponse(yard.get(i));
                List<PriceEntity> price = priceRepositories.findByIdYard(yard.get(i).getId());// Trả về giá
                List<OrderEntity> order = orderReposotories.findOrderInYard(yard.get(i).getId(), date);// Trả về các
                                                                                                       // order
                for (Integer j = 0; j < order.size(); j++) {// lọc qua các order
                    List<OrdersDetail> orderDetails = orderDetailRepositories.findByOrderId(order.get(j).getId());// Tìm
                    // các
                    // order
                    // item
                    OrderReponse temp1 = orderParseOrderReponse.parse(order.get(j), orderDetails);
                    orderReponses.add(temp1);
                }
                temp.setPrice(price);
                temp.setTimeEservations(orderReponses);
                yardReponses.add(temp);
            }
            return yardReponses;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public YardEntity craeteYard(YardRequest request) {
        try {
            YardEntity yard = mapper.parseToYard(request);
            yardRepositories.save(yard);
            return yard;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public boolean UpdateYardImage(UpdateYardImage file) {
        if (file.getImage().isEmpty()) {

            return false;
        }

        YardEntity yard = yardRepositories.findById(file.getYard_id())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if (yard.getImage() != null) {
            File file_delete = new File(UPLOAD_DIRECTORY + yard.getImageName());
            file_delete.delete();
        }

        String fileName = randomAlphaNumeric(10).concat("-" + file.getImage().getOriginalFilename());
        try {
            Files.copy(file.getImage().getInputStream(), this.root.resolve(fileName));
            yard.setImage(fileName);
            yardRepositories.save(yard);
        } catch (Exception e) {
            if (e instanceof FileUploadException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.print(e);
        }
        return true;
    }

    // =====================================================
    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

}

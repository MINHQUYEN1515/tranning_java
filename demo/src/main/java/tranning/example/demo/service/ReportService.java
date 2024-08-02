package tranning.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tranning.example.demo.Config.Constant;
import tranning.example.demo.model.ReportEntity;
import tranning.example.demo.reponsitories.ReportReposirories;

@Service
public class ReportService {
    @Autowired
    private ReportReposirories reportReposirories;

    public List<ReportEntity> getAllFilter(String type) {
        return reportReposirories.getAll(Constant.typeReport.get(type));
    }

    public List<ReportEntity> getAll() {
        return reportReposirories.findAll();
    }

}

package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tranning.example.demo.reponsitories.ReportReposirories;

@Service
public class ReportService {
    @Autowired
    private ReportReposirories reportReposirories;
}

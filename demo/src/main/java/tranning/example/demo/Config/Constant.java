package tranning.example.demo.Config;

import java.util.HashMap;

public class Constant {
    public static final HashMap<String, Integer> typeReport;
    static {
        typeReport = new HashMap<>();
        typeReport.put("report_delete", 1);
        typeReport.put("report_change_time", 2);
    }
}

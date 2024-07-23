package tranning.example.demo.Config;

import jakarta.servlet.http.HttpServletRequest;

public class AppConfig {
    public static String SECRET_KEY = "Yn7MAHdm9Gi9QnTk6F5jCsQatUNJW8zp";

    public static String getHost(HttpServletRequest request) {
        return request.getHeader("host");
    }
}

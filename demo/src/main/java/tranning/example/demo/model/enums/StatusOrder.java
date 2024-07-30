package tranning.example.demo.model.enums;

public enum StatusOrder {
    MOIDAT,
    DANG_SU_DUNG,
    TRA_SAN;

    public static Integer getStatus(String statu) {
        Integer status = 0;
        if (statu == "MOIDAT") {
            status = 1;
        } else if (statu == "DANG_SU_DUNG") {
            status = 2;
        } else if (statu == "TRA_SAN") {
            status = 3;
        }
        return status;
    }
}

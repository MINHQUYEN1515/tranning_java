package tranning.example.demo.model.enums;

public enum Status_Yard {
    CHUA_DAT,
    DA_DAT_KHUNG_SANG,
    DA_DAT_KHUNG_CHIEU,
    DA_DAT_KHUNG_TOI;

    public static String getStatus(Long price) {
        if (price == 120000) {
            return Status_Yard.DA_DAT_KHUNG_SANG.name();
        }
        if (price == 100000) {
            return Status_Yard.DA_DAT_KHUNG_CHIEU.name();
        }
        if (price == 150000) {
            return Status_Yard.DA_DAT_KHUNG_TOI.name();
        }
        return "";
    }
}

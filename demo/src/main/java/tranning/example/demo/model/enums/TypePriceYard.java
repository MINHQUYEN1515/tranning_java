package tranning.example.demo.model.enums;

public enum TypePriceYard {
    GIA_SANG,
    GIA_CHIEU,
    GIA_TOI;

    public Integer getPrice(String param) {
        Integer price = 0;
        if (param == "GIA_SANG") {
            price = 1;
        } else if (param == "GIA_CHIEU") {
            price = 2;
        } else if (param == "GIA_TOI") {
            price = 3;
        }
        return price;
    }
}

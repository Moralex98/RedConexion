package Models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductGranModel {
    private int idProductGra;
    private String nameGra;
    private int stock;
    private BigDecimal price;
}


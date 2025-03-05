
package Models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductLitModel {
    private int idProductLit;
    private String nameLit;
    private int stock;
    private BigDecimal price;
}

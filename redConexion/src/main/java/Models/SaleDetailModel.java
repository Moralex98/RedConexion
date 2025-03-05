
package Models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SaleDetailModel {
    private int id_Sale;
    private int id_Det;
    private int id_Lit;
    private int amount;
    private BigDecimal subtotal;
}

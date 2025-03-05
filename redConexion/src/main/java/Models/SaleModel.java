
package Models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SaleModel {
   private int idSale;
   private String date;
   private BigDecimal total;
}

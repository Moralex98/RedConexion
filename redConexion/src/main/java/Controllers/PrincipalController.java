
package Controllers;

import Models.Dao.ProductGranDao;
import Models.ProductGranModel;
import Views.PrincipalView;
import Views.ProductGranView;
import Views.SaleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrincipalController implements ActionListener{
    PrincipalView principalView;
    private final ProductGranDao productGranDao;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
        this.principalView.btnNewSale.addActionListener(this);
        this.principalView.btnProvider.addActionListener(this);
        this.principalView.btnProductGran.addActionListener(this);
        this.principalView.btnProductLit.addActionListener(this);
        this.principalView.btnSales.addActionListener(this);
        this.principalView.btnLogout.addActionListener(this);
        productGranDao = new ProductGranDao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.principalView.btnNewSale == e.getSource()) {
            SaleView saleView = new SaleView();
            saleView.setVisible(true);
        } 
        else if (this.principalView.btnProductGran == e.getSource()) {
            ProductGranView productGranView = new ProductGranView();
            ProductGraController productGra = new ProductGraController(productGranView);
            ArrayList <ProductGranModel> productModel = productGranDao.Read();
            productGra.showProducts(productModel);
            principalView.showJpanel(productGranView);
        }
    }
    
}


package Controllers;

import Models.Dao.ProductGranDao;
import Models.Dao.ProductLitDao;
import Models.ProductGranModel;
import Models.ProductLitModel;
import Views.Load;
import Views.LoginView;
import Views.PrincipalView;
import Views.ProductGranView;
import Views.ProductLitView;
import Views.SaleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrincipalController implements ActionListener{
    PrincipalView principalView;
    private final ProductGranDao productGranDao;
    private final ProductLitDao productLitDao;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
        this.principalView.btnNewSale.addActionListener(this);
        this.principalView.btnProvider.addActionListener(this);
        this.principalView.btnProductGran.addActionListener(this);
        this.principalView.btnProductLit.addActionListener(this);
        this.principalView.btnSales.addActionListener(this);
        this.principalView.btnLogout.addActionListener(this);
        productGranDao = new ProductGranDao();
        productLitDao = new ProductLitDao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.principalView.btnNewSale == e.getSource()) {
            SaleView saleView = new SaleView();
            principalView.showJpanel(saleView);
        } 
        else if (this.principalView.btnProductGran == e.getSource()) {
            ProductGranView productGranView = new ProductGranView();
            ProductGraController productGra = new ProductGraController(productGranView);
            ArrayList <ProductGranModel> productModel = productGranDao.Read();
            productGra.showProducts(productModel);
            principalView.showJpanel(productGranView);
        }
        else if (this.principalView.btnProductLit == e.getSource()) {
            ProductLitView productLitView = new ProductLitView();
            ProductLitController productLit = new ProductLitController(productLitView);
            ArrayList <ProductLitModel> productModel = productLitDao.Read();
            //productLit.showProducts(productModel);
            principalView.showJpanel(productLitView);
        }
        else if (this.principalView.btnLogout == e.getSource()) {
            logOut();
        }
    }
    
    private void logOut() {
        Load load = new Load();
        load.setVisible(true);
        
        new Thread(() -> {
            try {
                // Esperar 3 segundos (puedes cambiar este valor según tus necesidades)
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            // Después de cerrar la sesión, ocultar la vista de carga
            load.dispose();
            
            principalView.dispose();
            LoginView login = new LoginView();
            login.setVisible(true);
        }).start();
    }
}

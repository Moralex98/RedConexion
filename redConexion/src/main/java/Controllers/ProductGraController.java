package Controllers;

import Models.Dao.ProductGranDao;
import Models.ProductGranModel;
import Views.ProductGranView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ProductGraController implements ActionListener, MouseListener, KeyListener {
    ProductGranView productGranView;
    ProductGranDao productDao;
    DefaultTableModel model;
    ArrayList<ProductGranModel> productsGran;

    public ProductGraController(ProductGranView productGranView) {
        this.productGranView = productGranView;
        productDao = new ProductGranDao();
        model = (DefaultTableModel) productGranView.tblProductGra.getModel();
        this.productGranView.btnSaveP.addActionListener(this);
        this.productGranView.btnUpdateP.addActionListener(this);
        this.productGranView.btnDeleteP.addActionListener(this);
        this.productGranView.tblProductGra.addMouseListener(this);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (this.productGranView.btnSaveP == e.getSource()) {

        // Desactivar temporalmente el botón para evitar múltiples clics
        productGranView.btnSaveP.setEnabled(false);
        int id = Integer.parseInt(productGranView.txtCodP.getText());
        
        try {
            // Verifica si el producto ya existe en la base de datos antes de insertarlo
            if (!productDao.exists(productGranView.txtCodP.getText())) {
                ProductGranModel productGran = new ProductGranModel();
                productGran.setIdProductGra(id);
                productGran.setNameGra(productGranView.txtNameP.getText());
                productGran.setStock(Integer.parseInt(productGranView.txtStockP.getText()));
                productGran.setPrice(new BigDecimal(productGranView.txtPriceP.getText()));

                productDao.setProductGranelModel(productGran);
                productDao.Create();

                show(); // Asegúrate de que este método no esté ejecutando otro evento

                JOptionPane.showMessageDialog(null, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            } else{
                JOptionPane.showMessageDialog(null, "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Reactivar el botón después de la ejecución
            productGranView.btnSaveP.setEnabled(true);
        }
    }
}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    private void cleanData() {
        productGranView.txtCodP.setText("");
        productGranView.txtNameP.setText("");
        productGranView.txtStockP.setText("");
        productGranView.txtPriceP.setText("");
    }
    
    public void showProductGra(ArrayList<ProductGranModel> product) {
        model.setRowCount(0);
        if (product != null) {
            for (ProductGranModel products : product) {
                model.addRow(new Object [] {
                    products.getIdProductGra(),
                    products.getNameGra(),
                    products.getStock(),
                    products.getPrice()
                });
            }
        }
    }
    private void show() {
        ArrayList <ProductGranModel> product = productDao.Read();
        showProductGra(product);
    }
    
    private void fillInData (int selectRow){
        productGranView.txtCodP.setText(model.getValueAt(selectRow, 0).toString());
        productGranView.txtNameP.setText(model.getValueAt(selectRow, 1).toString());
        productGranView.txtStockP.setText(model.getValueAt(selectRow, 2).toString());
        productGranView.txtPriceP.setText(model.getValueAt(selectRow, 3).toString());
    }
    
}

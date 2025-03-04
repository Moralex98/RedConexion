package Controllers;

import Models.Dao.ProductGranDao;
import Models.EmptyFields;
import Models.ProductGranModel;
import Views.ProductGranView;
//import com.mysql.cj.result.Row;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;



public class ProductGraController implements ActionListener, MouseListener, KeyListener {
    ProductGranView productGranView;
    ProductGranDao productDao;
    DefaultTableModel model;
    ArrayList<ProductGranModel> productsGran;
    EmptyFields emptyFields = new EmptyFields();
    

    public ProductGraController(ProductGranView productGranView) {
        this.productGranView = productGranView;
        productDao = new ProductGranDao();
        model = (DefaultTableModel) productGranView.tblProductGra.getModel();
        this.productGranView.tblProductGra.addMouseListener(this);
        this.productGranView.txtSearch.addActionListener(this);
        this.productGranView.txtSearch.addKeyListener(this);
        
        productGranView.btnSaveP.addActionListener( e -> {
           if (productGranView.btnSaveP == e.getSource()) {
                      
                if(emptyData()) {
                    int id = Integer.parseInt(productGranView.txtCodP.getText());

                    // Verifica si el producto ya existe en la base de datos antes de insertarlo
                    if (!productDao.exists(id)) {
                        ProductGranModel productGran = new ProductGranModel();
                        productGran.setIdProductGra(id);
                        productGran.setNameGra(productGranView.txtNameP.getText().toUpperCase());
                        productGran.setStock(Integer.parseInt(productGranView.txtStockP.getText()));
                        productGran.setPrice(new BigDecimal(productGranView.txtPriceP.getText()));

                        productDao.setProductGranelModel(productGran);
                        productDao.Create();

                        show(); 
                        cleanData();

                        JOptionPane.showMessageDialog(null, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(null, "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
           }
        });
        
        this.productGranView.btnUpdateP.addActionListener( e -> {
            if(this.productGranView.btnUpdateP == e.getSource()) {
              
                if (emptyData()) {
                    int id = Integer.parseInt(productGranView.txtCodP.getText());

                    if (productDao.exists(id)) {
                        ProductGranModel productUpdate = new ProductGranModel();
                        productUpdate.setIdProductGra(id);

                        if(!productGranView.txtCodP.getText().isEmpty()) {
                          productUpdate.setIdProductGra(Integer.parseInt(productGranView.txtCodP.getText()));
                        }

                        if(!productGranView.txtNameP.getText().isEmpty()) {
                          productUpdate.setNameGra(productGranView.txtNameP.getText().toUpperCase());
                        }

                        if(!productGranView.txtStockP.getText().isEmpty()) {
                          productUpdate.setStock(Integer.parseInt(productGranView.txtStockP.getText()));
                        }

                        if(!productGranView.txtPriceP.getText().isEmpty()) {
                          productUpdate.setPrice(new BigDecimal(productGranView.txtPriceP.getText()));
                        }
                        ProductGranDao productUpd = new ProductGranDao();
                        productUpd.setProductGranelModel(productUpdate);
                        productUpd.Update();

                        show(); 
                        cleanData();

                        JOptionPane.showMessageDialog(null, "Actualización exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    } else{
                        JOptionPane.showMessageDialog(null, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        this.productGranView.btnDeleteP.addActionListener( e -> {
            if (this.productGranView.btnDeleteP == e.getSource()) {
                
                if (emptyData()) {   
                    int id = Integer.parseInt(productGranView.txtCodP.getText());

                    if (productDao.exists(id)) {
                        ProductGranModel productDelete = new ProductGranModel();
                        productDelete.setIdProductGra(id);

                        ProductGranDao productDel = new ProductGranDao();
                        productDel.setProductGranelModel(productDelete);
                        productDel.delete();

                        show(); 
                        cleanData();

                        JOptionPane.showMessageDialog(null, "Eliminación exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    } else{
                        JOptionPane.showMessageDialog(null, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.productGranView.btnExcel.addActionListener( e -> {
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selectedRow = productGranView.tblProductGra.getSelectedRow();
            
            if (selectedRow != -1) {
                fillInData(selectedRow);
            }
        }
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
        String search = productGranView.txtSearch.getText().toUpperCase();
        if (search.isEmpty()) {
            productsGran = productDao.Read();
            showProducts(productsGran);
        } else {
            search(search);
        }
    }
    private boolean emptyData() {
        if (emptyFields.emptyFields(productGranView.txtCodP, "codigo")) {
            productGranView.txtCodP.requestFocus();
            return false;
        }
        String name = productGranView.txtNameP.getText();
        if (!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios", "Error", JOptionPane.ERROR_MESSAGE);
            productGranView.txtNameP.requestFocus();
            return false;
        }
        String stockText = productGranView.txtStockP.getText().trim(); // Elimina espacios en blanco

        if (emptyFields.emptyFields(productGranView.txtStockP, "stock")) {
            productGranView.txtStockP.requestFocus();
            return false;
        }

        if (!stockText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El campo solo debe contener números", "Error", JOptionPane.ERROR_MESSAGE);
            productGranView.txtStockP.requestFocus();
            return false;
        }
        String priceText = productGranView.txtPriceP.getText().trim();
        
        if (emptyFields.emptyFields(productGranView.txtPriceP, "Precio")) {
            productGranView.txtPriceP.requestFocus();
            return false;
        }
        if (!priceText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El campo solo debe contener números", "Error", JOptionPane.ERROR_MESSAGE);
            productGranView.txtPriceP.requestFocus();
            return false;
        } 
        return true;
    }
    
    private void cleanData() {
        productGranView.txtCodP.setText("");
        productGranView.txtNameP.setText("");
        productGranView.txtStockP.setText("");
        productGranView.txtPriceP.setText("");
    }
    
    public void showProducts(ArrayList<ProductGranModel> product) {
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
        showProducts(product);
    }
    private void search(String search) {
        DefaultTableModel model = (DefaultTableModel) productGranView.tblProductGra.getModel();
        ArrayList<ProductGranModel> products = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i, 1).toString();

            if (model.getValueAt(i, 0).toString().contains(search) || name.contains(search)) {
                ProductGranModel product = new ProductGranModel();

                try {
                    int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                    product.setIdProductGra(id);
                } catch (NumberFormatException e) {
                    product.setIdProductGra(0); 
                }

                product.setNameGra(name);

                try {
                    int stock = Integer.parseInt(model.getValueAt(i, 2).toString());
                    product.setStock(stock);
                } catch (NumberFormatException e) {
                    product.setStock(0);
                }

                try {
                    BigDecimal price = new BigDecimal(model.getValueAt(i, 3).toString());
                    product.setPrice(price);
                } catch (NumberFormatException e) {
                    product.setPrice(BigDecimal.ZERO);
                }

                products.add(product);
            }
        }

        showProducts(products);
    }
    
    private void fillInData (int selectRow){
        productGranView.txtCodP.setText(model.getValueAt(selectRow, 0).toString());
        productGranView.txtNameP.setText(model.getValueAt(selectRow, 1).toString());
        productGranView.txtStockP.setText(model.getValueAt(selectRow, 2).toString());
        productGranView.txtPriceP.setText(model.getValueAt(selectRow, 3).toString());
    }
    
    private void exportToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos Granel"); // CORRECTO: org.apache.poi.ss.usermodel.Sheet

        // Encabezados
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Código", "Producto", "Stock", "Precio"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Datos de la tabla
        for (int i = 0; i < productGranView.tblProductGra.getRowCount(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < productGranView.tblProductGra.getColumnCount(); j++) {
                row.createCell(j).setCellValue(productGranView.tblProductGra.getValueAt(i, j).toString());
            }
        }

        // Guardar en la carpeta Documentos del usuario
        String documentsPath = getDocumentsFolder();
        String filePath = documentsPath + "/Productos_Granel.xlsx";

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            JOptionPane.showMessageDialog(null, "Archivo Excel guardado en: " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getDocumentsFolder() {
        String userHome = System.getProperty("user.home");

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            return userHome + "\\Documents"; // Windows
        } else {
            return userHome + "/Documents"; // Mac
        }
    }

    
}

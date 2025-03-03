
package Models.Dao;

import Models.Conection;
import Models.ProductGranModel;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductGranDao implements Crud{
    private ProductGranModel productGranelModel;
    Conection conection = new Conection();

    
    @Override
    public void Create() {
        conection.connectDatabase();
        try {
            String query = "INSERT INTO productgranel (id_gran, nombre, stock, precio) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = conection.connection.prepareStatement(query);
            preparedStatement.setInt(1, productGranelModel.getIdProductGra());
            preparedStatement.setString(2, productGranelModel.getNameGra());
            preparedStatement.setInt(3, productGranelModel.getStock());
            preparedStatement.setBigDecimal(4, productGranelModel.getPrice());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public ArrayList<ProductGranModel> Read() {
        ArrayList<ProductGranModel> productGranel = new ArrayList<>();
        ProductGranModel product;
        conection.connectDatabase();
        try {
            ResultSet result = conection.connection.createStatement().executeQuery("SELECT * FROM productgranel");
            while (result.next()) {
                product = new ProductGranModel();
                product.setIdProductGra(result.getInt("id_gran"));
                product.setNameGra(result.getString("nombre"));
                product.setStock(result.getInt("stock"));
                product.setPrice(result.getBigDecimal("precio")); // Uso de BigDecimal
                productGranel.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conection.connection != null) conection.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productGranel;
    }
    
    @Override
    public void Update() {
        conection.connectDatabase();
        Statement stmt = null;
        
        try {
            if (productGranelModel.getIdProductGra() != 0 && 
               (productGranelModel.getNameGra() != null || 
               productGranelModel.getStock() != 0 || 
               productGranelModel.getPrice().compareTo(BigDecimal.ZERO) != 0)) {

                StringBuilder query = new StringBuilder("UPDATE productgra SET ");
                boolean firstField = true;
                
                if (productGranelModel.getNameGra() != null) {
                    query.append("nombre = '").append(productGranelModel.getNameGra()).append("'");
                    firstField = false;
                }
                
                if (productGranelModel.getStock() != 0) {
                    if (!firstField) query.append(", ");
                    query.append("stock = ").append(productGranelModel.getStock());
                    firstField = false;
                }
              
                if (productGranelModel.getPrice().compareTo(BigDecimal.ZERO) != 0) {
                    if (!firstField) query.append(", ");
                    query.append("precio = ").append(productGranelModel.getPrice().toPlainString());
                }
                
                query.append(" WHERE id_gran = ").append(productGranelModel.getIdProductGra());
                
                stmt = conection.connection.createStatement();
                stmt.executeUpdate(query.toString());
                System.out.println("Actualización exitosa");
            } else {
                System.err.println("Se requiere al menos un campo adicional para actualizar.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conection.connection != null) conection.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete() {
        conection.connectDatabase();
        Statement stmt= null;
        try {
            if (productGranelModel.getIdProductGra() != 0) {
                String query = "DELETE FROM productgra WHERE id_gran = " + productGranelModel.getIdProductGra();
                
                stmt = conection.connection.createStatement();
                int rowsAffected = stmt.executeUpdate(query);
                
                if (rowsAffected > 0) {
                    System.out.println("Consulta eliminada con exito");
                } else {
                    System.out.println("No se encontro el regisro para eliminar");
                }
            } else {
                System.err.println("El ID es necesario para eliminar un registro.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) stmt.close();
                    if (conection.connection != null) conection.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductGranModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ProductGranModel getProductGranelModel() {
        return productGranelModel;
    }

    public void setProductGranelModel(ProductGranModel productGranelModel) {
        this.productGranelModel = productGranelModel;
    }
    
    public boolean exists(String id) {
    try {
        String query = "SELECT COUNT(*) FROM productgranel WHERE id_gran = ?";
        PreparedStatement ps = conection.connection.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            return true; // Ya existe
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // No existe
}

    
    
}

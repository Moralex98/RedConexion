
package Models.Dao;

import Models.Conection;
import Models.ProductGranModel;
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
            conection.connection.createStatement().execute(
                    "INSERT INTO VALUES productgra VALUES("
                    +productGranelModel.getIdProductGra()+",'"
                    +productGranelModel.getNameGra()+"',"
                    +productGranelModel.getStock()+","
                    +productGranelModel.getPrice()+")"
            );
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ProductGranModel> Read() {
        ArrayList<ProductGranModel>productGranel = new ArrayList<>();
        ProductGranModel product;
        conection.connectDatabase();
        try {
            ResultSet result = conection.connection.createStatement().executeQuery("SELECT * FROM productgra");
            while(result.next()) {
                product = new ProductGranModel();
                product.setIdProductGra(result.getInt("id_gran"));
                product.setNameGra(result.getString("nombre"));
                product.setStock(result.getInt("stock"));
                product.setPrice(result.getFloat("Precio"));
                productGranel.add(product);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productGranel;
    }

    @Override
    public void Update() {
        conection.connectDatabase();
        Statement stmt = null;
        
        try {
            if (productGranelModel.getIdProductGra() != 0 && (productGranelModel.getNameGra() != null || (productGranelModel.getPrice() != 0 || (productGranelModel.getStock()) != 0))) {
                StringBuilder query = new StringBuilder("UPDATE productgra SET ");
                 
                boolean firstField = true;
                
                if (productGranelModel.getNameGra() != null) {
                    query.append("nombre = '").append(productGranelModel.getNameGra()).append("'");
                    firstField = false;
                }
                
                if (productGranelModel.getStock() != 0) {
                    if (!firstField) query.append(", ");
                    query.append("stock = '").append(productGranelModel.getStock()).append("'");
                    firstField = false;
                }
              
                if (productGranelModel.getPrice() != 0) {
                    if (!firstField) query.append(", ");
                    query.append("precio = '").append((float) productGranelModel.getPrice()).append("'");
                }
                
                query.append(" Where id_gran = ").append(productGranelModel.getIdProductGra());
                
                stmt = conection.connection.createStatement();
                stmt.executeUpdate(query.toString());
                System.out.println("Actualizacion exitosa");
            }else {
                System.err.println("Se requiere al menos un campo adicional para actualizar.");
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
}

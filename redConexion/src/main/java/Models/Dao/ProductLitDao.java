package Models.Dao;

import Models.Conection;
import Models.ProductGranModel;
import Models.ProductLitModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductLitDao {
    ProductLitModel productLiterModel;
    Conection conection = new Conection();

    /*
    @Override
    public void Create() {
        conection.connectDatabase();
        try {
            conection.connection.createStatement().execute(
                    "INSERT INTO VALUES productliter VALUES("
                    +productLiterModel.getIdProductLit()+",'"
                    +productLiterModel.getNameLit()+"',"
                    +productLiterModel.getStock()+","
                    +productLiterModel.getPrice()+")"
            );
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public ArrayList<ProductLitModel> Read() {
        ArrayList<ProductLitModel>productLiter = new ArrayList<>();
        ProductLitModel product;
        conection.connectDatabase();
        try {
            ResultSet result = conection.connection.createStatement().executeQuery("SELECT * FROM productgra");
            while(result.next()) {
                product = new ProductLitModel();
                product.setIdProductLit(result.getInt("id_lit"));
                product.setNameLit(result.getString("nombre"));
                product.setStock(result.getInt("stock"));
                product.setPrice(result.getFloat("Precio"));
                productLiter.add(product);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productLiter;
    }

    @Override
    public void Update() {
        conection.connectDatabase();
        Statement stmt = null;
        
        try {
            if (productLiterModel.getIdProductLit() != 0 && (productLiterModel.getNameLit() != null || (productLiterModel.getPrice() != 0 || (productLiterModel.getStock()) != 0))) {
                StringBuilder query = new StringBuilder("UPDATE productliter SET ");
                 
                boolean firstField = true;
                
                if (productLiterModel.getNameLit() != null) {
                    query.append("nombre = '").append(productLiterModel.getNameLit()).append("'");
                    firstField = false;
                }
                
                if (productLiterModel.getStock() != 0) {
                    if (!firstField) query.append(", ");
                    query.append("stock = '").append(productLiterModel.getStock()).append("'");
                    firstField = false;
                }
              
                if (productLiterModel.getPrice() != 0) {
                    if (!firstField) query.append(", ");
                    query.append("precio = '").append((float) productLiterModel.getPrice()).append("'");
                }
                
                query.append(" Where id_lit = ").append(productLiterModel.getIdProductLit());
                
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
            if (productLiterModel.getIdProductLit() != 0) {
                String query = "DELETE FROM productgra WHERE id_gran = " + productLiterModel.getIdProductLit();
                
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
    }  */
}

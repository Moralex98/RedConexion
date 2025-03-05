package Models.Dao;

import Models.ProductLitModel;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductLitDao implements Crud{
     private ProductLitModel productLiterModel;
    Conection connection = new Conection();

    
    
    @Override
    public void Create() {
        connection.connectDatabase(); // Abre la conexión
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO productgranel (id_gran, nombre, stock, precio) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, productLiterModel.getIdProductLit());
            preparedStatement.setString(2, productLiterModel.getNameLit());
            preparedStatement.setInt(3, productLiterModel.getStock());
            preparedStatement.setBigDecimal(4, productLiterModel.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @Override
        public ArrayList<ProductLitModel> Read() {
            ArrayList<ProductLitModel> productLiterList = new ArrayList<>();
            connection.connectDatabase();
            String query = "SELECT * FROM productlit";

            try (Statement stmt = connection.getConnection().createStatement();
                ResultSet result = stmt.executeQuery(query)) {

                while (result.next()) {
                    ProductLitModel product = new ProductLitModel();
                    product.setIdProductLit(result.getInt("id_lit"));
                    product.setNameLit(result.getString("nombre"));
                    product.setStock(result.getInt("stock"));
                    product.setPrice(result.getBigDecimal("precio"));
                    productLiterList.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductLitDao.class.getName()).log(Level.SEVERE, "Error al leer productos", ex);
            }
            return productLiterList;
        }  
    
        @Override
        public void Update() {
            connection.connectDatabase();

            if (productLiterModel == null || productLiterModel.getIdProductLit() == 0) {
                System.err.println("Error: No hay datos válidos para actualizar.");
                return;
            }

            StringBuilder query = new StringBuilder("UPDATE productliter SET ");
            boolean firstField = false;

            if (productLiterModel.getNameLit() != null) {
                query.append("nombre = ?");
                firstField = true;
            }

            if (productLiterModel.getStock() > 0) {
                if (firstField) query.append(", ");
                query.append("stock = ?");
                firstField = true;
            }

            if (productLiterModel.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                if (firstField) query.append(", ");
                query.append("precio = ?");
            }

            query.append(" WHERE id_lit = ?");

            try (PreparedStatement stmt = connection.getConnection().prepareStatement(query.toString())) {
                int index = 1;

                if (productLiterModel.getNameLit() != null) {
                    stmt.setString(index++, productLiterModel.getNameLit());
                }

                if (productLiterModel.getStock() > 0) {
                    stmt.setInt(index++, productLiterModel.getStock());
                }

                if (productLiterModel.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                    stmt.setBigDecimal(index++, productLiterModel.getPrice());
                }

                stmt.setInt(index, productLiterModel.getIdProductLit());

                stmt.executeUpdate();
                System.out.println("Actualización exitosa.");
            } catch (SQLException ex) {
                Logger.getLogger(ProductLitDao.class.getName()).log(Level.SEVERE, "Error al actualizar producto", ex);
            }
        }
        
        @Override
        public void delete() {
            connection.connectDatabase();

            if (productLiterModel == null || productLiterModel.getIdProductLit() == 0) {
                System.err.println("Error: El ID es necesario para eliminar un registro.");
                return;
            }

            String query = "DELETE FROM productliter WHERE id_lit = ?";

            try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
                stmt.setInt(1, productLiterModel.getIdProductLit());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Producto eliminado con éxito.");
                } else {
                    System.out.println("No se encontró el registro para eliminar.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductLitDao.class.getName()).log(Level.SEVERE, "Error al eliminar producto", ex);
            }
        }

        public ProductLitModel getProductLiterModel() {
            return productLiterModel;
        }

        public void setProductLiterModel(ProductLitModel productLiterModel) {
            this.productLiterModel = productLiterModel;
        }

        public boolean exists(int id) {
            connection.connectDatabase();
            String query = "SELECT COUNT(*) FROM productliter WHERE id_lit = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductLitDao.class.getName()).log(Level.SEVERE, "Error al verificar existencia", ex);
        }
        return false;
    }

}
   

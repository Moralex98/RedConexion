
package Models.Dao;

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
    Conection connection = new Conection();

    
    @Override
    public void Create() {
        connection.connectDatabase(); // Abre la conexión
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO productgranel (id_gran, nombre, stock, precio) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, productGranelModel.getIdProductGra());
            preparedStatement.setString(2, productGranelModel.getNameGra());
            preparedStatement.setInt(3, productGranelModel.getStock());
            preparedStatement.setBigDecimal(4, productGranelModel.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ProductGranModel> Read() {
        ArrayList<ProductGranModel> productGranelList = new ArrayList<>();
        connection.connectDatabase();
        String query = "SELECT * FROM productgranel";
        
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {
                ProductGranModel product = new ProductGranModel();
                product.setIdProductGra(result.getInt("id_gran"));
                product.setNameGra(result.getString("nombre"));
                product.setStock(result.getInt("stock"));
                product.setPrice(result.getBigDecimal("precio"));
                productGranelList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, "Error al leer productos", ex);
        }
        return productGranelList;
    }
    
    @Override
    public void Update() {
        connection.connectDatabase();

        if (productGranelModel == null || productGranelModel.getIdProductGra() == 0) {
            System.err.println("Error: No hay datos válidos para actualizar.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE productgranel SET ");
        boolean firstField = false;

        if (productGranelModel.getNameGra() != null) {
            query.append("nombre = ?");
            firstField = true;
        }

        if (productGranelModel.getStock() > 0) {
            if (firstField) query.append(", ");
            query.append("stock = ?");
            firstField = true;
        }

        if (productGranelModel.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            if (firstField) query.append(", ");
            query.append("precio = ?");
        }

        query.append(" WHERE id_gran = ?");

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query.toString())) {
            int index = 1;

            if (productGranelModel.getNameGra() != null) {
                stmt.setString(index++, productGranelModel.getNameGra());
            }

            if (productGranelModel.getStock() > 0) {
                stmt.setInt(index++, productGranelModel.getStock());
            }

            if (productGranelModel.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                stmt.setBigDecimal(index++, productGranelModel.getPrice());
            }

            stmt.setInt(index, productGranelModel.getIdProductGra());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        connection.connectDatabase();

        if (productGranelModel == null || productGranelModel.getIdProductGra() == 0) {
            System.err.println("Error: El ID es necesario para eliminar un registro.");
            return;
        }

        String query = "DELETE FROM productgranel WHERE id_gran = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, productGranelModel.getIdProductGra());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("No se encontró el registro para eliminar.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, "Error al eliminar producto", ex);
        }
    }

    public ProductGranModel getProductGranelModel() {
        return productGranelModel;
    }

    public void setProductGranelModel(ProductGranModel productGranelModel) {
        this.productGranelModel = productGranelModel;
    }
    
    public boolean exists(int id) {
        connection.connectDatabase();
        String query = "SELECT COUNT(*) FROM productgranel WHERE id_gran = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductGranDao.class.getName()).log(Level.SEVERE, "Error al verificar existencia", ex);
        }
        return false;
    }

    
    
}

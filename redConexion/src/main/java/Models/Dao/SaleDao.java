
package Models.Dao;

import Models.SaleModel;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaleDao implements Crud{
    private SaleModel saleModel;
    Conection connection = new Conection();

    @Override
    public void Create() {
         connection.connectDatabase();;
        PreparedStatement preparedStatement = null;
        
         try {
            String query = "INSERT INTO ventas (fecha,total) Values (?, ?)";
            
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(2, saleModel.getDate());
            preparedStatement.setBigDecimal(3, saleModel.getTotal());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<SaleModel> Read() {
        ArrayList<SaleModel> saleList = new ArrayList<>();
        connection.connectDatabase();
        String query = "SELECT * FROM ventas";
        
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet result = stmt.executeQuery(query);
            
            while (result.next()) {
                SaleModel saleModels = new SaleModel();
                saleModels.setIdSale(result.getInt("id"));
                saleModels.setDate(result.getString("fecha"));
                saleModels.setTotal(result.getBigDecimal("total"));
                saleList.add(saleModels);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saleList;
    }

    @Override
    public void Update() {
        connection.connectDatabase();

        if (saleModel == null || saleModel.getIdSale() == 0) {
            System.err.println("Error: No hay datos válidos para actualizar.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE ventas SET ");
        boolean firstField = true;

        if (saleModel.getDate() != null) {
            if (!firstField) query.append(", ");
            query.append("fecha = ?");
            firstField = false;
        }

        if (saleModel.getTotal().compareTo(BigDecimal.ZERO) > 0) {
            if (!firstField) query.append(", ");
            query.append("total = ?");
            firstField = false;
        }

        // Asegúrate de que la cláusula WHERE esté correctamente formulada
        query.append(" WHERE id = ?");

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query.toString())) {
            int index = 1;

            if (saleModel.getDate() != null) {
                stmt.setString(index++, saleModel.getDate());
            }

            if (saleModel.getTotal().compareTo(BigDecimal.ZERO) > 0) {
                stmt.setBigDecimal(index++, saleModel.getTotal());
            }

            // Aquí se establecen los valores para la cláusula WHERE
            stmt.setInt(index++, saleModel.getIdSale());  // venta_id

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        connection.connectDatabase();
        
        if (saleModel == null || saleModel.getIdSale() == 0) {
            System.err.println("Error: El ID es necesario para eliminar un registro.");
            return;
        }
        String query = "DELETE FROM ventas WHERE id = ?";
        
        try (PreparedStatement stmt = connection.connection.prepareStatement(query)) {
            stmt.setInt(1, saleModel.getIdSale());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("venta eliminada con éxito.");
            } else {
                System.out.println("No se encontró el registro para eliminar.");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

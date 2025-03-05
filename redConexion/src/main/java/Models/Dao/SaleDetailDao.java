
package Models.Dao;

import Models.SaleDetailModel;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDetailDao implements Crud {
    private SaleDetailModel saleDetailModel;
    Conection connection = new Conection();

    @Override
    public void Create() {
        connection.connectDatabase();
        PreparedStatement preparedStatement = null;
        
        try {
            String query = "INSERT INTO detalle_ventas (venta_id, id, id_lit, cantidad, subtotal) Values (?, ?, ?, ?, ?)";
            
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, saleDetailModel.getId_Sale());
            preparedStatement.setInt(2, saleDetailModel.getId_Det());
            preparedStatement.setInt(3, saleDetailModel.getId_Lit());
            preparedStatement.setInt(4, saleDetailModel.getAmount());
            preparedStatement.setBigDecimal(5, saleDetailModel.getSubtotal());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SaleDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<SaleDetailModel> Read() {
        ArrayList<SaleDetailModel> saleDetailList = new ArrayList<>();
        connection.connectDatabase();
        String query = "SELECT * FROM detalle_ventas";
        
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet result = stmt.executeQuery(query);
            
            while (result.next()) {
                SaleDetailModel saleDetail = new SaleDetailModel();
                saleDetail.setId_Sale(result.getInt("venta_id"));
                saleDetail.setId_Det(result.getInt("id"));
                saleDetail.setId_Lit(result.getInt("id_lit"));
                saleDetail.setAmount(result.getInt("cantidad"));
                saleDetail.setSubtotal(result.getBigDecimal("subtotal"));
                saleDetailList.add(saleDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saleDetailList;
    }

    @Override
    public void Update() {
        connection.connectDatabase();

        if (saleDetailModel == null || saleDetailModel.getId_Sale() == 0) {
            System.err.println("Error: No hay datos válidos para actualizar.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE detalle_ventas SET ");
        boolean firstField = true;

        if (saleDetailModel.getAmount() > 0) {
            if (!firstField) query.append(", ");
            query.append("cantidad = ?");
            firstField = false;
        }

        if (saleDetailModel.getSubtotal().compareTo(BigDecimal.ZERO) > 0) {
            if (!firstField) query.append(", ");
            query.append("subtotal = ?");
            firstField = false;
        }

        // Asegúrate de que la cláusula WHERE esté correctamente formulada
        query.append(" WHERE venta_id = ? AND id = ?");

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query.toString())) {
            int index = 1;

            if (saleDetailModel.getAmount() > 0) {
                stmt.setInt(index++, saleDetailModel.getAmount());
            }

            if (saleDetailModel.getSubtotal().compareTo(BigDecimal.ZERO) > 0) {
                stmt.setBigDecimal(index++, saleDetailModel.getSubtotal());
            }

            // Aquí se establecen los valores para la cláusula WHERE
            stmt.setInt(index++, saleDetailModel.getId_Sale());  // venta_id
            stmt.setInt(index, saleDetailModel.getId_Det());     // id

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SaleDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        connection.connectDatabase();
        
        if (saleDetailModel == null || saleDetailModel.getId_Sale() == 0) {
            System.err.println("Error: El ID es necesario para eliminar un registro.");
            return;
        }
        String query = "DELETE FROM detalle_ventas WHERE venta_id = ?";
        
        try (PreparedStatement stmt = connection.connection.prepareStatement(query)) {
            stmt.setInt(1, saleDetailModel.getId_Sale());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("venta eliminada con éxito.");
            } else {
                System.out.println("No se encontró el registro para eliminar.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SaleDetailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

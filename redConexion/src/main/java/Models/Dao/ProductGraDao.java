
package Models.Dao;

import Models.Conection;
import Models.ProductGranelModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductGraDao implements Crud{
    private ProductGranelModel productGranelModel;
    Conection conection = new Conection();

    
    @Override
    public void Create() {
        conection.connectDatabase();
        try {
            conection.connection.createStatement().execute(
                    "INSERT INTO VALUES productgra VALUES("
                    +productGranelModel.getIdProductGra()+",'"
                    +productGranelModel.getNombre()+"',"
                    +productGranelModel.getStock()+","
                    +productGranelModel.getPrecio()+")"
            );
        } catch (SQLException ex) {
            Logger.getLogger(ProductGraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList Read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update() {
    }

    @Override
    public void delete() {
    }
    
}


package Models.Dao;

import Models.Conection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDao implements Crud{
    private SaleDao saleDao;
    Conection conection = new Conection();

    @Override
    public void Create() {
        conection.connectDatabase();
            
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

    public SaleDao getSaleDao() {
        return saleDao;
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }
    
    
    
}

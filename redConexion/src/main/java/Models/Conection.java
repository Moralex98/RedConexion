
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conection {
    Connection connection = null;
    private boolean isConnected = false;
  
    public Connection getConnection() {
        return connection;
    }
     
    public boolean connectDatabase() {
        try {
            try { 
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
                return false;
            }
           
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/redconexion", // Cambia el puerto y el nombre de la BD si es necesario
                    "root", "FreddyMora");
 
            isConnected = connection.isValid(50000);
        } catch (SQLException sqle) {
            System.out.println("Error en la conexión: " + sqle);
            isConnected = false;
        }
        return isConnected;
    } 
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex);
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }

    public static void main(String[] args) {
        Conection javaMySQL = new Conection();
        javaMySQL.connectDatabase(); 
        if (javaMySQL.isConnected()) {
            System.out.println("Conexión exitosa");
        } else {
            System.out.println("Fallo en la conexión");
        }
    }
}

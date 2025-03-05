package Models.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public Connection connection = null;
    private boolean isConnected = false;

    public Connection getConnection() {
        return connection;
    }

    public boolean connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver MySQL
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/redconexion", // URL de la BD
                    "root", "FreddyMora"); // Usuario y contraseña

            isConnected = connection.isValid(50000);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error en la conexión: " + ex.getMessage());
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
            System.err.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
    
}
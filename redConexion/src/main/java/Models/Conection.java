package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conection {
    private Connection connection = null;
    private boolean isConnected = false;
    private static final Logger LOGGER = Logger.getLogger(Conection.class.getName());

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
            if (isConnected) {
                LOGGER.log(Level.INFO, "🔗 Conexión a la base de datos establecida correctamente.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            LOGGER.log(Level.SEVERE, "❌ Error al conectar con la base de datos: {0}", ex.getMessage());
            isConnected = false;
        }
        return isConnected;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                LOGGER.log(Level.INFO, "🔒 Conexión a la base de datos cerrada correctamente.");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "⚠️ Error al cerrar la conexión: {0}", ex.getMessage());
        }
    }
}


package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;


public class LoginVerifier {
     Conection objBD = new Conection();

    // Método para verificar usuario con contraseña encriptada
    public boolean verifyUser(String username, String password) {
        objBD.connectDatabase();
        boolean verifier = false;
        String sql = "SELECT password_hash FROM usuarios WHERE username = ?";

        try (PreparedStatement statement = objBD.connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    String storedHash = resultado.getString("password_hash");
                    if (BCrypt.checkpw(password, storedHash)) {
                        verifier = true; // Contraseña correcta
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginVerifier.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("❌ Error al conectar a la base de datos: " + ex.getMessage());
        }

        return verifier;
    }

    // Método para obtener el rol del usuario
    public String getRolUser(String username) {
        objBD.connectDatabase(); // Conecta a la base de datos
        String rol = null;
        String sql = "SELECT rol FROM usuarios WHERE username = ?";

        try (PreparedStatement statement = objBD.connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    rol = resultado.getString("rol");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginVerifier.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("❌ Error al conectar a la base de datos: " + ex.getMessage());
        }

        return rol;
    }
}

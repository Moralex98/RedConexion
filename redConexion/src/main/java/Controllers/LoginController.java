
package Controllers;

import Models.LoginVerifier;
import Views.Load;
import Views.LoginView;
import Views.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;


public class LoginController implements ActionListener, KeyListener{
    private LoginView loginView;
    private LoginVerifier loginVerifier;
    

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginVerifier = new LoginVerifier();
        this.loginView.btnEnter.addActionListener(this);
        this.loginView.chkPassword.addActionListener(this);
        this.loginView.txtPassword.addKeyListener(this);
        
    }
    //ocultar ventana
    public void hideWindow() {
        loginView.setVisible(false);
    }
    //mostrar ventana
    public void showWindow() {
        loginView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.loginView.btnEnter == e.getSource()) {
            getLogin();
        } else if (this.loginView.chkPassword == e.getSource()) {
            if (loginView.chkPassword.isSelected()) {
                loginView.txtPassword.setEchoChar((char) 0);
            } else {
                loginView.txtPassword.setEchoChar('*');
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            getLogin();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    //realizar login
    private void getLogin(){
        String user = loginView.txtUser.getText().toLowerCase();
        String password = loginView.txtPassword.getText().toLowerCase();
        
        if (!user.isEmpty() && !password.isEmpty()) {
            if (loginVerifier.verifyUser(user, password)) {
                String rol = loginVerifier.getRolUser(user);
                if (rol != null) {
                    if (rol.equals("admin")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido administrador " +user);
                        login();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa un usario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void login() {
        Load load = new Load();
        load.setVisible(true);
        
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            load.dispose();
            loginView.dispose();
            PrincipalView principal = new PrincipalView();
            principal.setVisible(true);
        }).start();
    }
}

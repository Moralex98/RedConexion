package Views;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class Load extends JDialog{
    private JProgressBar progressBar;
    
    public Load() {
        setTitle("Cargando...");
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(300, 100); //tamaño fijo
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        getContentPane().add(progressBar, BorderLayout.CENTER);
    }
}

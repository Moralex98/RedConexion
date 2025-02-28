
package Controllers;

import Views.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener{
    PrincipalView principalView;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
        this.principalView.btnNewSale.addActionListener(this);
        this.principalView.btnProvider.addActionListener(this);
        this.principalView.btnProductGran.addActionListener(this);
        this.principalView.btnProductLit.addActionListener(this);
        this.principalView.btnSales.addActionListener(this);
        this.principalView.btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.principalView.btnNewSale == e.getSource()) {
            
        }  
    }
    
}

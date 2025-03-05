
package Models.Others;

import javax.swing.JOptionPane;

public class EmptyFields {
    public boolean emptyFields (javax.swing.JTextField emptyField,String Field){
        if(emptyField.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Campo requerido "+Field);
            return true;
        }
         return false;
    }
}

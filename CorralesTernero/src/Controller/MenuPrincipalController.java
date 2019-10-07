package Controller;

import Models.*;
import Views.*;
import java.awt.event.*;

/**
 *
 * @author Carlos Contreras
 */
public class MenuPrincipalController implements ActionListener {
    
    private MenuPrincipalModel menuPrincipalModel;
    private MenuPrincipalView menuPrincipalView;
    
    public MenuPrincipalController(MenuPrincipalModel menuPrincipalModel, MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalModel = menuPrincipalModel;
        this.menuPrincipalView = menuPrincipalView;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO: Añadir escuchadores
        if (evt.getSource() == menuPrincipalView.getAñadirCria()) {
            System.out.println("Llego");
        }
    }
    
}

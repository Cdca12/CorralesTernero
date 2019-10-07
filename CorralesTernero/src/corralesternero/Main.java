package corralesternero;

import Controllers.MenuPrincipalController;
import Utils.Tema;
import Models.*;
import Views.*;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {
        Tema.cambiarTema();

        MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
        MenuPrincipalModel menuPrincipalModel = new MenuPrincipalModel();
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(menuPrincipalModel, menuPrincipalView);

        menuPrincipalView.setController(menuPrincipalController);
        menuPrincipalView.launchView();

    }

}

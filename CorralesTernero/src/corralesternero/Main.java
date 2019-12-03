package corralesternero;

import Controllers.LoginController;
import Controllers.MenuPrincipalController;
import DataAccesor.SQLConnectionHelper;
import Utils.Tema;
import Models.*;
import Utils.Token;
import Views.*;
import java.awt.Dialog;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.util.*;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {
        Tema.cambiarTema();

        loginTerminal();
            
        if (!LoginView.isIsLogged()) {
            return;
        }

        MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
        MenuPrincipalModel menuPrincipalModel = new MenuPrincipalModel();
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(menuPrincipalModel, menuPrincipalView);

        menuPrincipalView.setController(menuPrincipalController);
        menuPrincipalView.launchView();

    }

    private static void loginTerminal() {
        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel, loginView);

        loginView.setController(loginController);
        loginView.launchView();
        
    }


}

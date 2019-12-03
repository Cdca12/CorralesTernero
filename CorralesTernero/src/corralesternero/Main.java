package corralesternero;

import Controllers.MenuPrincipalController;
import DataAccesor.SQLConnectionHelper;
import Utils.Tema;
import Models.*;
import Utils.Token;
import Views.*;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {        
        Tema.cambiarTema();
        
        loginTerminal();

        MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
        MenuPrincipalModel menuPrincipalModel = new MenuPrincipalModel();
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(menuPrincipalModel, menuPrincipalView);

        menuPrincipalView.setController(menuPrincipalController);
        menuPrincipalView.launchView();

    }
    
    private static void loginTerminal() {
        String user, pass;
        Scanner sc = new Scanner(System.in);
        System.out.println("Login CorralesTernero");
        System.out.print("Username: ");
        user = sc.next();
        System.out.print("Password: ");
        pass = sc.next();
        
        Token token = new Token(user, pass);
        SQLConnectionHelper.setToken(token);
    }

}

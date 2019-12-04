package Controllers;

import DataAccesor.SQLConnectionHelper;
import Models.LoginModel;
import Utils.Token;
import Views.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 *
 * @author Carlos Contreras
 */
public class LoginController implements ActionListener {
    
    private LoginModel loginModel;
    private LoginView loginView;
    
    public LoginController(LoginModel loginModel, LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginView.getBtnSalir()) {
            Token.expireToken();
            loginView.dispose();
            return;
        }
        if (ae.getSource() == loginView.getBtnLogin()) {
            String username = loginView.getTxtUsername().getText();
            String password = loginView.getTxtPassword().getText();
            
            if (!loginModel.login(new Token(username, password))) {
                Token.expireToken();
                loginView.mostrarMensajeError();
                loginView.getTxtUsername().setText("");
                loginView.getTxtPassword().setText("");
                return;
            }
            Token.refreshToken();
            loginView.dispose();
            return;
        }
    }
    
}

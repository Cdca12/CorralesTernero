package Views;

import Controllers.LoginController;
import Utils.Status;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Contreras
 */
public class LoginView extends JDialog {

    private LoginController loginController;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnSalir;
    private static boolean isLogged;

    public LoginView() {
        setTitle("Iniciar sesión");
        setSize(350, 210);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    public void initComponents() {
        lbUsername = new JLabel("Usuario: ");
        lbUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lbUsername.setBounds(55, 40, 100, 20);
        add(lbUsername);
        
        lbPassword = new JLabel("Contraseña");
        lbPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPassword.setBounds(35, lbUsername.getY() + 40, 150, 20);
        add(lbPassword);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(lbUsername.getX() + lbUsername.getWidth() + 10, lbUsername.getY(), 150, 30);
        add(txtUsername);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(txtUsername.getX(), lbPassword.getY(), 150, 30);
        add(txtPassword);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(70, lbPassword.getY() + 50, 100, 30);
        add(btnSalir);
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(btnSalir.getX() + btnSalir.getWidth() + 10, btnSalir.getY(), 100, 30);
        add(btnLogin);
        
        
    }
    
    public void launchView() {
        setVisible(true);
    }
    
    public void setController(LoginController loginController) {
        this.loginController = loginController;
        addListeners();
    }

    private void addListeners() {
        btnLogin.addActionListener(loginController);
        btnSalir.addActionListener(loginController);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public static boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }
    
    public void mostrarMensajeError() {
        JOptionPane.showMessageDialog(null, Status.ERROR_LOGIN.MESSAGE, Status.ERROR_LOGIN.TITLE, JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    
    
}

package Views;

import Controllers.AñadirSensoresController;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirSensoresView extends JDialog {
    
    private AñadirSensoresController añadirSensoresController;

    private JLabel lbMarca, lbCantidad;
    private JTextField txtMarca, txtCantidad;
    private JButton btnComprar, btnLimpiar;
    
    public AñadirSensoresView() {
        setTitle("Añadir Sensores");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }
    
    private void initComponents() {
        // Labels
        lbMarca = new JLabel("Marca");
        lbMarca.setFont(new Font("Arial", Font.PLAIN, 14));
        lbMarca.setBounds(93, 80, 100, 20);
        add(lbMarca);

        lbCantidad = new JLabel("Cantidad");
        lbCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCantidad.setBounds(90, lbMarca.getY() + 40, 100, 20);
        add(lbCantidad);

        // TextFields
        txtMarca = new JTextField();
        txtMarca.setBounds(165, lbMarca.getY() - 5, 150, 30);
        add(txtMarca);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(165, lbCantidad.getY() - 5, 150, 30);
        add(txtCantidad);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 210, 100, 30);
        add(btnLimpiar);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnComprar);
    }
    
    public void launchView() {
        setVisible(true);
    }

    public void setController(AñadirSensoresController añadirSensoresController) {
        this.añadirSensoresController = añadirSensoresController;
        addListeners();
    }
    
    private void addListeners() {
        btnComprar.addActionListener(añadirSensoresController);
        btnLimpiar.addActionListener(añadirSensoresController);
    }
    
    public void limpiarCampos() {
        txtMarca.setText("");
        txtCantidad.setText("");
        txtMarca.requestFocus();
    }
    
    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextField getTxtMarca() {
        return txtMarca;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JButton getBtnComprar() {
        return btnComprar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    
}

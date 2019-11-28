package Views;

import Controllers.*;
import Entities.*;
import java.awt.Font;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirAlimentosView extends JDialog {

    private AñadirAlimentosController añadirAlimentosController;

    private JLabel lbNombre, lbCantidad;
    private JTextField txtNombre, txtCantidad;
    private JButton btnAñadir, btnLimpiar;

    public AñadirAlimentosView() {
        setTitle("Añadir Alimentos");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        // Labels
        lbNombre = new JLabel("Nombre");
        lbNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        lbNombre.setBounds(93, 80, 100, 20);
        add(lbNombre);

        lbCantidad = new JLabel("Cantidad");
        lbCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCantidad.setBounds(90, lbNombre.getY() + 40, 100, 20);
        add(lbCantidad);

        // TextFields
        txtNombre = new JTextField();
        txtNombre.setBounds(165, lbNombre.getY() - 5, 150, 30);
        add(txtNombre);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(165, lbCantidad.getY() - 5, 150, 30);
        add(txtCantidad);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 210, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);

    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(AñadirAlimentosController añadirAlimentosController) {
        this.añadirAlimentosController = añadirAlimentosController;
        addListeners();
    }
    
    private void addListeners() {
        btnAñadir.addActionListener(añadirAlimentosController);
        btnLimpiar.addActionListener(añadirAlimentosController);
    }
    
    public void limpiarCampos() {
        txtNombre.setText("");
        txtCantidad.setText("");
        txtNombre.requestFocus();
    }
    
    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
    
    

}

package Views;

import Controllers.AñadirCriaController;
import Entities.*;
import java.awt.Font;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    // TODO: Añadir un logo o un ícono de una cría
    private AñadirCriaController añadirCriaController;

    private JLabel lbCorralID, lbPesoID, lbGrasaCoberturaID, lbMusculoID,
            lbEstadoCriaID, lbDietaID;
    private JTextField txtCorralID, txtPesoID, txtGrasaCoberturaID, txtMusculoID,
            txtEstadoCriaID, txtDietaID;
    private JButton btnAñadir, btnLimpiar;

    public AñadirCriaView() {
        setTitle("Añadir Cría");
        setSize(600, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        
        initComponents();
    }

    private void initComponents() {

        // Labels
        lbCorralID = new JLabel("CorralID");
        lbCorralID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCorralID.setBounds(190, 150, 100, 20);
        add(lbCorralID);

        lbPesoID = new JLabel("PesoID");
        lbPesoID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPesoID.setBounds(190, lbCorralID.getY() + 40, 100, 20);
        add(lbPesoID);

        lbGrasaCoberturaID = new JLabel("GrasaCoberturaID");
        lbGrasaCoberturaID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbGrasaCoberturaID.setBounds(130, lbPesoID.getY() + 40, 150, 20);
        add(lbGrasaCoberturaID);

        lbMusculoID = new JLabel("MusculoID");
        lbMusculoID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbMusculoID.setBounds(180, lbGrasaCoberturaID.getY() + 40, 150, 20);
        add(lbMusculoID);

        lbEstadoCriaID = new JLabel("EstadoCriaID");
        lbEstadoCriaID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbEstadoCriaID.setBounds(165, lbMusculoID.getY() + 40, 150, 20);
        add(lbEstadoCriaID);

        lbDietaID = new JLabel("DietaID");
        lbDietaID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDietaID.setBounds(200, lbEstadoCriaID.getY() + 40, 150, 20);
        add(lbDietaID);

        // TextFields
        txtCorralID = new JTextField();
        txtCorralID.setBounds(270, 145, 150, 30);
        add(txtCorralID);

        txtPesoID = new JTextField();
        txtPesoID.setBounds(txtPesoID.getX() + 270, txtCorralID.getY() + 40, 150, 30);
        add(txtPesoID);

        txtGrasaCoberturaID = new JTextField();
        txtGrasaCoberturaID.setBounds(270, txtPesoID.getY() + 40, 150, 30);
        add(txtGrasaCoberturaID);

        txtMusculoID = new JTextField();
        txtMusculoID.setBounds(270, txtGrasaCoberturaID.getY() + 40, 150, 30);
        add(txtMusculoID);

        txtEstadoCriaID = new JTextField();
        txtEstadoCriaID.setBounds(270, txtMusculoID.getY() + 40, 150, 30);
        add(txtEstadoCriaID);

        txtDietaID = new JTextField();
        txtDietaID.setBounds(270, txtEstadoCriaID.getY() + 40, 150, 30);
        add(txtDietaID);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 420, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);

    }

    public void launchView() {
        cargarInformacion();
        setVisible(true);
    }

    public void setController(AñadirCriaController añadirCriaController) {
        this.añadirCriaController = añadirCriaController;
        addListeners();
    }

    private void addListeners() {
        btnAñadir.addActionListener(añadirCriaController);
        btnLimpiar.addActionListener(añadirCriaController);
    }

    public void limpiarCampos() {
        txtCorralID.setText("");
        txtPesoID.setText("");
        txtGrasaCoberturaID.setText("");
        txtMusculoID.setText("");
        txtEstadoCriaID.setText("");
        txtDietaID.setText("");
        txtCorralID.requestFocus();
    }
    
    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void cargarInformacion() {
        cargarPesos(añadirCriaController.obtenerPesos());
        cargarGrasasCobertura();
    }
    
    private void cargarPesos(List<Peso> listaPesos) {
        for(Peso peso : listaPesos) {
            System.out.println("PesoID:\t" + peso.getPesoID() + "CondicionCorporal:\t" + peso.getCondicionCorporal());
        }
    }
    
    private void cargarGrasasCobertura() {

    }
    
    private void cargarMusculos() {
        
    }
    
    private void cargarEstadosCria() {
        
    }
    
    private void cargarDietas() {
        
    }


    // Getters y Setters
    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTextField getTxtCorralID() {
        return txtCorralID;
    }

    public void setTxtCorralID(JTextField txtCorralID) {
        this.txtCorralID = txtCorralID;
    }

    public JTextField getTxtPesoID() {
        return txtPesoID;
    }

    public void setTxtPesoID(JTextField txtPesoID) {
        this.txtPesoID = txtPesoID;
    }

    public JTextField getTxtGrasaCoberturaID() {
        return txtGrasaCoberturaID;
    }

    public void setTxtGrasaCoberturaID(JTextField txtGrasaCoberturaID) {
        this.txtGrasaCoberturaID = txtGrasaCoberturaID;
    }

    public JTextField getTxtMusculoID() {
        return txtMusculoID;
    }

    public void setTxtMusculoID(JTextField txtMusculoID) {
        this.txtMusculoID = txtMusculoID;
    }

    public JTextField getTxtEstadoCriaID() {
        return txtEstadoCriaID;
    }

    public void setTxtEstadoCriaID(JTextField txtEstadoCriaID) {
        this.txtEstadoCriaID = txtEstadoCriaID;
    }

    public JTextField getTxtDietaID() {
        return txtDietaID;
    }

    public void setTxtDietaID(JTextField txtDietaID) {
        this.txtDietaID = txtDietaID;
    }
}

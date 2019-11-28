package Views;

import Controllers.AñadirCorralesController;
import Entities.*;
import java.awt.Font;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCorralesView extends JDialog {

    private AñadirCorralesController añadirCorralesController;

    private JComboBox cmbTipoCorral, cmbEstados;
    private JLabel lbTipoCorral, lbEstado, lbTipoMusculo, lbPeso, lbGrasa;
    private JButton btnAñadir, btnLimpiar;

    public AñadirCorralesView() {
        setTitle("Añadir Corrales");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    public void initComponents() {
        // Labels
        lbTipoCorral = new JLabel("Tipo Corral");
        lbTipoCorral.setFont(new Font("Arial", Font.PLAIN, 14));
        lbTipoCorral.setBounds(70, 80, 100, 20);
        add(lbTipoCorral);

        lbEstado = new JLabel("Estado");
        lbEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        lbEstado.setBounds(90, lbTipoCorral.getY() + 40, 100, 20);
        add(lbEstado);

        // ComboBox
        cmbTipoCorral = new JComboBox();
        cmbTipoCorral.setBounds(170, lbTipoCorral.getY() - 5, 185, 30);
        add(cmbTipoCorral);

        cmbEstados = new JComboBox();
        cmbEstados.setBounds(170, lbEstado.getY() - 5, 185, 30);
        add(cmbEstados);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 210, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);

    }

    public void launchView() {
        cargarInformacion();
        setVisible(true);
    }

    public void setController(AñadirCorralesController añadirCorralesController) {
        this.añadirCorralesController = añadirCorralesController;
        addListeners();
    }

    private void addListeners() {
        btnAñadir.addActionListener(añadirCorralesController);
        btnLimpiar.addActionListener(añadirCorralesController);
    }

    public void limpiarCampos() {
        cmbTipoCorral.setSelectedIndex(0);
        cmbEstados.setSelectedIndex(0);
        cmbTipoCorral.requestFocus();
    }

    public void cargarInformacion() {
//        cargarTiposMusculo(añadirCriaController.obtenerTiposMusculo());
        cargarTipoCorral(añadirCorralesController.obtenerTipoCorral());
        cargarEstados(añadirCorralesController.obtenerEstados());
    }

    private void cargarTipoCorral(List<TipoCorral> listaTipoCorral) {
        cmbTipoCorral.setModel(new DefaultComboBoxModel(listaTipoCorral.toArray()));
        cmbTipoCorral.insertItemAt("Seleccionar una opcíón", 0);
        cmbTipoCorral.setSelectedIndex(0);
    }
    
    private void cargarEstados(List<Estados> listaTipoCorral) {
        cmbEstados.setModel(new DefaultComboBoxModel(listaTipoCorral.toArray()));
        cmbEstados.insertItemAt("Seleccionar una opcíón", 0);
        cmbEstados.setSelectedIndex(0);
    }

    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    public JComboBox getCmbTipoCorral() {
        return cmbTipoCorral;
    }

    public JComboBox getCmbEstados() {
        return cmbEstados;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
    
    

}

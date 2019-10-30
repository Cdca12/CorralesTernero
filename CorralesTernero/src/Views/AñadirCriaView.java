package Views;

import Models.*;
import Controllers.*;
import Entities.*;
import java.awt.Font;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    // TODO: Añadir un logo o un ícono de una cría
    private AñadirCriaController añadirCriaController;

    private JLabel lbCorral, lbPeso, lbGrasaCobertura, lbTipoMusculo, lbDieta, lbKg;
    // TODO: Evitar usar static y pasar parámetro del TextField a modificar
    private static JTextField txtCorral, txtPeso, txtGrasaCobertura, txtTipoMusculo, txtDieta;
    private JComboBox cmbTipoMusculo, cmbGrasaCobertura;
    private JButton btnAñadir, btnLimpiar, btnSeleccionarCorral, btnSeleccionarDieta;
    private String dietaID;

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
        lbCorral = new JLabel("Corral");
        lbCorral.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCorral.setBounds(190, 150, 100, 20);
        add(lbCorral);

        lbDieta = new JLabel("Dieta");
        lbDieta.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDieta.setBounds(200, lbCorral.getY() + 40, 100, 20);
        add(lbDieta);

        lbTipoMusculo = new JLabel("Tipo Músculo");
        lbTipoMusculo.setFont(new Font("Arial", Font.PLAIN, 14));
        lbTipoMusculo.setBounds(150, lbDieta.getY() + 40, 150, 20);
        add(lbTipoMusculo);

        lbGrasaCobertura = new JLabel("Grasa Cobertura");
        lbGrasaCobertura.setFont(new Font("Arial", Font.PLAIN, 14));
        lbGrasaCobertura.setBounds(130, lbTipoMusculo.getY() + 40, 150, 20);
        add(lbGrasaCobertura);

        lbPeso = new JLabel("Peso");
        lbPeso.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPeso.setBounds(200, lbGrasaCobertura.getY() + 40, 150, 20);
        add(lbPeso);
        
        lbKg = new JLabel("kg");
        lbKg.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKg.setBounds(lbPeso.getX() + 170, lbPeso.getY(), 150, 20);
        add(lbKg);

        // TextFields
        txtCorral = new JTextField();
        txtCorral.setBounds(270, 145, 90, 30);
        txtCorral.setEditable(false);
        add(txtCorral);

        txtDieta = new JTextField();
        txtDieta.setBounds(270, txtCorral.getY() + 40, 90, 30);
        txtDieta.setEditable(false);
        add(txtDieta);

        txtPeso = new JTextField();
        txtPeso.setBounds(270, txtDieta.getY() + 120, 90, 30);
//        txtPeso.setEditable(false);
        add(txtPeso);

        // ComboBox
        cmbTipoMusculo = new JComboBox();
        cmbTipoMusculo.setBounds(270, txtDieta.getY() + 40, 185, 30);
        add(cmbTipoMusculo);

        cmbGrasaCobertura = new JComboBox();
        cmbGrasaCobertura.setBounds(270, cmbTipoMusculo.getY() + 40, 185, 30);
        add(cmbGrasaCobertura);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 410, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);

        btnSeleccionarCorral = new JButton("Seleccionar");
        btnSeleccionarCorral.setBounds(txtCorral.getX() + 90, txtCorral.getY(), 95, 30);
        add(btnSeleccionarCorral);

        btnSeleccionarDieta = new JButton("Seleccionar");
        btnSeleccionarDieta.setBounds(txtDieta.getX() + 90, txtDieta.getY(), 95, 30);
        add(btnSeleccionarDieta);

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
        btnSeleccionarDieta.addActionListener(añadirCriaController);
    }

    public void limpiarCampos() {
        txtCorral.setText("");
        txtDieta.setText("");
        cmbTipoMusculo.setSelectedIndex(0);
        cmbGrasaCobertura.setSelectedIndex(0);
        txtPeso.setText("");
        btnSeleccionarCorral.requestFocus();
    }

    public void cargarInformacion() {
        cargarGrasasCobertura(añadirCriaController.obtenerGrasasCobertura());
        cargarTiposMusculo(añadirCriaController.obtenerTiposMusculo());
    }

    private void cargarGrasasCobertura(List<GrasaCobertura> listaGrasasCobertura) {
        cmbGrasaCobertura.setModel(new DefaultComboBoxModel(listaGrasasCobertura.toArray()));
        cmbGrasaCobertura.insertItemAt("Selecciona una opcíón", 0);
        cmbGrasaCobertura.setSelectedIndex(0);
    }

    private void cargarTiposMusculo(List<Musculo> listaMusculos) {
        cmbTipoMusculo.setModel(new DefaultComboBoxModel(listaMusculos.toArray()));
        cmbTipoMusculo.insertItemAt("Seleccionar opcíón", 0);
        cmbTipoMusculo.setSelectedIndex(0);
    }

    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters y Setters
    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnSeleccionarDieta() {
        return btnSeleccionarDieta;
    }

    public JTextField getTxtCorral() {
        return txtCorral;
    }

    public JTextField getTxtPeso() {
        return txtPeso;
    }

    public JTextField getTxtGrasaCobertura() {
        return txtGrasaCobertura;
    }

    public JTextField getTxtMusculo() {
        return txtTipoMusculo;
    }

    public JTextField getTxtDieta() {
        return txtDieta;
    }

    public static void setDietaID(String dietaID) {
        txtDieta.setText(dietaID);
    }

    // Métodos para abrir las consultas
    public void abrirDietas() {
        DietasView dietasView = new DietasView();
        DietasModel dietasModel = new DietasModel();
        DietasController añadirCriaController = new DietasController(dietasModel, dietasView);

        dietasView.setController(añadirCriaController);
        dietasView.launchView();
    }

}

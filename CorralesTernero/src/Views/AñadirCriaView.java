package Views;

import Models.*;
import Controllers.*;
import Entities.*;
import java.awt.Font;
//import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    // TODO: Añadir un logo o un ícono de una cría
    private AñadirCriaController añadirCriaController;

    private JLabel lbCorralID, lbPesoID, lbGrasaCoberturaID, lbMusculoID, lbDietaID;
    private JComboBox cmbCorral, cmbPeso, cmbGrasaCobertura, cmbMusculo;
    private static JTextField txtDieta;
    private JButton btnAñadir, btnLimpiar, btnSeleccionarDieta;
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
        // ComboBox
        cmbCorral = new JComboBox();
        cmbCorral.setBounds(270, 145, 170, 30);
        add(cmbCorral);

        cmbPeso = new JComboBox();
        cmbPeso.setBounds(270, cmbCorral.getY() + 40, 170, 30);
        add(cmbPeso);

        cmbGrasaCobertura = new JComboBox();
        cmbGrasaCobertura.setBounds(270, cmbPeso.getY() + 40, 170, 30);
        add(cmbGrasaCobertura);

        cmbMusculo = new JComboBox();
        cmbMusculo.setBounds(270, cmbGrasaCobertura.getY() + 40, 170, 30);
        add(cmbMusculo);

        txtDieta = new JTextField();
        txtDieta.setBounds(270, cmbMusculo.getY() + 40, 170, 30);
        txtDieta.setEditable(false);
        add(txtDieta);

        // Labels
        lbCorralID = new JLabel("Corral");
        lbCorralID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCorralID.setBounds(190, 150, 100, 20);
        add(lbCorralID);

        lbPesoID = new JLabel("Peso");
        lbPesoID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPesoID.setBounds(200, lbCorralID.getY() + 40, 100, 20);
        add(lbPesoID);

        lbGrasaCoberturaID = new JLabel("Grasa Cobertura");
        lbGrasaCoberturaID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbGrasaCoberturaID.setBounds(130, lbPesoID.getY() + 40, 150, 20);
        add(lbGrasaCoberturaID);

        lbMusculoID = new JLabel("Tipo Musculo");
        lbMusculoID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbMusculoID.setBounds(150, lbGrasaCoberturaID.getY() + 40, 150, 20);
        add(lbMusculoID);

        lbDietaID = new JLabel("Dieta");
        lbDietaID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDietaID.setBounds(200, lbMusculoID.getY() + 40, 150, 20);
        add(lbDietaID);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 410, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);
        
        btnSeleccionarDieta = new JButton("Seleccionar");
        btnSeleccionarDieta.setBounds(450, cmbMusculo.getY() + 40, 100, 30);
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
        cmbCorral.setSelectedIndex(0);
        cmbPeso.setSelectedIndex(0);
        cmbGrasaCobertura.setSelectedIndex(0);
        cmbMusculo.setSelectedIndex(0);
        txtDieta.setText(null); // Null o " "
        cmbCorral.requestFocus();
    }

    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    public void cargarInformacion() {
        cargarCorrales(añadirCriaController.obtenerCorrales());
        cargarPesos(añadirCriaController.obtenerPesos());
        cargarGrasasCobertura(añadirCriaController.obtenerGrasasCobertura());
        cargarMusculos(añadirCriaController.obtenerMusculos());
//        cargarDietas(añadirCriaController.obtenerDietas());
    }

    private void cargarCorrales(List<Corrales> listaCorrales) {
        cmbCorral.setModel(new DefaultComboBoxModel(listaCorrales.toArray()));
        cmbCorral.insertItemAt("Seleccionar...", 0);
        cmbCorral.setSelectedIndex(0);
    }

    private void cargarPesos(List<Peso> listaPesos) {
        cmbPeso.setModel(new DefaultComboBoxModel(listaPesos.toArray()));
        cmbPeso.insertItemAt("Seleccionar...", 0);
        cmbPeso.setSelectedIndex(0);
    }

    private void cargarGrasasCobertura(List<GrasaCobertura> listaGrasasCobertura) {
        cmbGrasaCobertura.setModel(new DefaultComboBoxModel(listaGrasasCobertura.toArray()));
        cmbGrasaCobertura.insertItemAt("Seleccionar...", 0);
        cmbGrasaCobertura.setSelectedIndex(0);
    }

    private void cargarMusculos(List<Musculo> listaMusculos) {
        cmbMusculo.setModel(new DefaultComboBoxModel(listaMusculos.toArray()));
        cmbMusculo.insertItemAt("Seleccionar...", 0);
        cmbMusculo.setSelectedIndex(0);
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
    
    public JComboBox getCmbCorral() {
        return cmbCorral;
    }

    public JComboBox getCmbPeso() {
        return cmbPeso;
    }

    public JComboBox getCmbGrasaCobertura() {
        return cmbGrasaCobertura;
    }

    public JComboBox getCmbMusculo() {
        return cmbMusculo;
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

package Views;

import Models.*;
import Controllers.*;
import Entities.*;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import Utils.Tipo;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    private AñadirCriaController añadirCriaController;

    private JLabel lbCorral, lbDieta, lbTipoMusculo, lbPeso, lbGrasa;
    private static JTextField txtCorral, txtDieta;
    private JTextField txtPeso, txtGrasa;
    private JComboBox cmbTipoMusculo;
    private JButton btnAñadir, btnLimpiar, btnSeleccionarCorral, btnSeleccionarDieta;

    public AñadirCriaView() {
        setTitle("Añadir Cría");
        setSize(400, 350);
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
        lbCorral.setBounds(90, 50, 100, 20);
        add(lbCorral);

        lbDieta = new JLabel("Dieta");
        lbDieta.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDieta.setBounds(100, lbCorral.getY() + 40, 100, 20);
        add(lbDieta);

        lbTipoMusculo = new JLabel("Tipo Músculo");
        lbTipoMusculo.setFont(new Font("Arial", Font.PLAIN, 14));
        lbTipoMusculo.setBounds(60, lbDieta.getY() + 40, 150, 20);
        add(lbTipoMusculo);

        lbPeso = new JLabel("Peso");
        lbPeso.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPeso.setBounds(100, lbTipoMusculo.getY() + 40, 150, 20);
        add(lbPeso);

        JLabel lbKgPeso = new JLabel("kg");
        lbKgPeso.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKgPeso.setBounds(270, lbPeso.getY(), 150, 20);
        add(lbKgPeso);

        lbGrasa = new JLabel("Grasa");
        lbGrasa.setFont(new Font("Arial", Font.PLAIN, 14));
        lbGrasa.setBounds(95, lbPeso.getY() + 40, 150, 20);
        add(lbGrasa);

        JLabel lbKgGrasa = new JLabel("%");
        lbKgGrasa.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKgGrasa.setBounds(270, lbGrasa.getY(), 150, 20);
        add(lbKgGrasa);

        // TextFields
        txtCorral = new JTextField();
        txtCorral.setBounds(170, 45, 90, 30);
        txtCorral.setEditable(false);
        add(txtCorral);

        txtDieta = new JTextField();
        txtDieta.setBounds(170, txtCorral.getY() + 40, 90, 30);
        txtDieta.setEditable(false);
        add(txtDieta);

        txtPeso = new JTextField();
        txtPeso.setBounds(170, txtDieta.getY() + 80, 90, 30);
        add(txtPeso);

        txtGrasa = new JTextField();
        txtGrasa.setBounds(170, txtPeso.getY() + 40, 90, 30);
        add(txtGrasa);

        // ComboBox
        cmbTipoMusculo = new JComboBox();
        cmbTipoMusculo.setBounds(170, txtDieta.getY() + 40, 185, 30);
        add(cmbTipoMusculo);

        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 260, 100, 30);
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
        btnSeleccionarCorral.addActionListener(añadirCriaController);
        btnSeleccionarDieta.addActionListener(añadirCriaController);
    }

    public void limpiarCampos() {
        txtCorral.setText("");
        txtDieta.setText("");
        cmbTipoMusculo.setSelectedIndex(0);
        txtPeso.setText("");
        txtGrasa.setText("");
        btnSeleccionarCorral.requestFocus();
    }

    public void cargarInformacion() {
        cargarTiposMusculo(añadirCriaController.obtenerTiposMusculo());
    }

    private void cargarTiposMusculo(List<Musculo> listaMusculos) {
        cmbTipoMusculo.setModel(new DefaultComboBoxModel(listaMusculos.toArray()));
        cmbTipoMusculo.insertItemAt("Seleccionar una opcíón", 0);
        cmbTipoMusculo.setSelectedIndex(0);
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

    public JButton getBtnSeleccionarCorral() {
        return btnSeleccionarCorral;
    }

    public JTextField getTxtCorral() {
        return txtCorral;
    }

    public JTextField getTxtPeso() {
        return txtPeso;
    }

    public JTextField getTxtDieta() {
        return txtDieta;
    }

    public JTextField getTxtGrasa() {
        return txtGrasa;
    }

    public JComboBox getCmbTipoMusculo() {
        return cmbTipoMusculo;
    }

    public static void setCorralID(String corralID) {
        txtCorral.setText(corralID);
    }

    public static void setDietaID(String dietaID) {
        txtDieta.setText(dietaID);
    }

    // Métodos para abrir las consultas
    public void abrirSeleccionDietas() {
        ConsultarDietasView dietasView = new ConsultarDietasView(Tipo.SELECCION);
        ConsultarDietasModel dietasModel = new ConsultarDietasModel();
        ConsultarDietasController dietasController = new ConsultarDietasController(dietasModel, dietasView);

        dietasView.setController(dietasController);
        dietasView.launchView();
    }

    public void abrirSeleccionCorrales() {
        ConsultarCorralesView corralesView = new ConsultarCorralesView(Tipo.SELECCION);
        ConsultarCorralesModel corralesModel = new ConsultarCorralesModel();
        ConsultarCorralesController corralesController = new ConsultarCorralesController(corralesModel, corralesView);

        corralesView.setController(corralesController);
        corralesView.launchView();
    }

    public void showErrorMessage(String ERROR_MESSAGE, String ERROR_TITLE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_MESSAGE, String OK_TITLE) {
        JOptionPane.showMessageDialog(null, OK_MESSAGE, OK_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

}

package Views;

import Controllers.*;
import Entities.*;
import Models.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Carlos Contreras
 */
public class AñadirDietasView extends JDialog {
    
    private AñadirDietasController añadirDietasController;
    
    private JLabel lbDiasTratamiento, lbAlimentoID;
    private static JTextField txtAlimentoID, txtDiasTratamiento;
    private JButton btnAñadir, btnLimpiar, btnSeleccionarAlimento;
    
    
    public AñadirDietasView() {
        setTitle("Añadir Dietas");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }
    
    private void initComponents() {
        // Labels
        lbAlimentoID = new JLabel("Alimento");
        lbAlimentoID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbAlimentoID.setBounds(90, 80, 100, 20);
        add(lbAlimentoID);
        
        lbDiasTratamiento = new JLabel("Dias Tratamiento");
        lbDiasTratamiento.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDiasTratamiento.setBounds(50, lbAlimentoID.getY() + 40, 120, 20);
        add(lbDiasTratamiento);
        
        // TextFields
        txtAlimentoID = new JTextField();
        txtAlimentoID.setBounds(180, lbAlimentoID.getY() - 5, 90, 30);
        txtAlimentoID.setEditable(false);
        add(txtAlimentoID);
        
        txtDiasTratamiento = new JTextField();
        txtDiasTratamiento.setBounds(180, lbDiasTratamiento.getY() - 5, 90, 30);
        add(txtDiasTratamiento);
        
        // Buttons
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 210, 100, 30);
        add(btnLimpiar);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(btnLimpiar.getX() + 110, btnLimpiar.getY(), 100, 30);
        add(btnAñadir);

        btnSeleccionarAlimento = new JButton("Seleccionar");
        btnSeleccionarAlimento.setBounds(txtAlimentoID.getX() + 90, txtAlimentoID.getY(), 95, 30);
        add(btnSeleccionarAlimento);
    }
    
    public void launchView() {
        setVisible(true);
    }
    
    public void setController(AñadirDietasController añadirDietasController) {
        this.añadirDietasController = añadirDietasController;
        addListeners();
    }

    private void addListeners() {
        btnAñadir.addActionListener(añadirDietasController);
        btnLimpiar.addActionListener(añadirDietasController);
        btnSeleccionarAlimento.addActionListener(añadirDietasController);
    }
    
    public void limpiarCampos() {
        txtAlimentoID.setText("");
        txtDiasTratamiento.setText("");
        btnSeleccionarAlimento.requestFocus();
    }
    
    public void showErrorMessage(String ERROR_TITLE, String ERROR_MESSAGE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_TITLE, String OK_MESSAGE) {
        JOptionPane.showMessageDialog(null, OK_TITLE, OK_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    public static JTextField getTxtDiasTratamiento() {
        return txtDiasTratamiento;
    }

    public static JTextField getTxtAlimentoID() {
        return txtAlimentoID;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnSeleccionarAlimento() {
        return btnSeleccionarAlimento;
    }
    
    public static void setAlimentoID(String alimentoID) {
        txtAlimentoID.setText(alimentoID);
    }
    
    // Métodos para abrir las consultas
    public void abrirAlimentos() {
        AlimentosView alimentosView = new AlimentosView();
        AlimentosModel alimentosModel = new AlimentosModel();
        AlimentosController alimentosController = new AlimentosController(alimentosModel, alimentosView);

        alimentosView.setController(alimentosController);
        alimentosView.launchView();
    }
}

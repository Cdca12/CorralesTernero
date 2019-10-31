package Views;

import Controllers.CorralesController;
import Controllers.SimularSeñalesController;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Contreras
 */
public class SimularSeñalesView extends JDialog {
    
    private SimularSeñalesController simularSeñalesController;
    private JLabel lbSensorID, lbNumeroSimulaciones;
    private JTextField txtSensorID, txtNumeroSimulaciones;
    private JButton btnIniciarSimulacion;
    
    public SimularSeñalesView() {
        setTitle("Simular Señales");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        // Labels
        lbSensorID = new JLabel("SensorID");
        lbSensorID.setFont(new Font("Arial", Font.PLAIN, 14));
        lbSensorID.setBounds(55, 40, 100, 20);
        add(lbSensorID);

        lbNumeroSimulaciones = new JLabel("# Simulaciones");
        lbNumeroSimulaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        lbNumeroSimulaciones.setBounds(35, lbSensorID.getY() + 40, 150, 20);
        add(lbNumeroSimulaciones);
        
        // TextFields
        txtSensorID = new JTextField();
        txtSensorID.setBounds(150, lbSensorID.getY() -5 , 100, 30);
        add(txtSensorID);

        txtNumeroSimulaciones = new JTextField();
        txtNumeroSimulaciones.setBounds(txtSensorID.getX(), lbNumeroSimulaciones.getY() - 5, 100, 30);
        add(txtNumeroSimulaciones);
        
        // Buttons
        btnIniciarSimulacion = new JButton("Iniciar Simulación");
        btnIniciarSimulacion.setBounds(50, 120, 200, 30);
        add(btnIniciarSimulacion);

    }
    
    public void launchView() {
        setVisible(true);
    }
    
    public void setController(SimularSeñalesController simularSeñalesController) {
        this.simularSeñalesController = simularSeñalesController;
        addListeners();
    }

    private void addListeners() {
        btnIniciarSimulacion.addActionListener(simularSeñalesController);
    }

    public JTextField getTxtSensorID() {
        return txtSensorID;
    }

    public JTextField getTxtNumeroSimulaciones() {
        return txtNumeroSimulaciones;
    }

    public JButton getBtnIniciarSimulacion() {
        return btnIniciarSimulacion;
    }
    
    public void limpiarCampos() {
        txtSensorID.setText("");
        txtNumeroSimulaciones.setText("");
        txtSensorID.requestFocus();
    }
    
    
}

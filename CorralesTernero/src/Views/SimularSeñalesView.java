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
    private JLabel lbSensorID, lbPresionArterial, lbPulso, lbTemperatura, lbNumeroSimulaciones;
    private JTextField txtSensorID, txtPresionArterial, txtPulso, txtTemperatura, txtNumeroSimulaciones;
    private JButton btnIniciarSimulacion;
    
    public SimularSeñalesView() {
        setTitle("Simular Señales");
        setSize(300, 350);
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

        lbPresionArterial = new JLabel("Presion Arterial");
        lbPresionArterial.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPresionArterial.setBounds(30, lbSensorID.getY() + 40, 150, 20);
        add(lbPresionArterial);

        lbPulso = new JLabel("Pulso");
        lbPulso.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPulso.setBounds(65, lbPresionArterial.getY() + 40, 150, 20);
        add(lbPulso);

        lbTemperatura = new JLabel("Temperatura");
        lbTemperatura.setFont(new Font("Arial", Font.PLAIN, 14));
        lbTemperatura.setBounds(40, lbPulso.getY() + 40, 150, 20);
        add(lbTemperatura);
        
        lbNumeroSimulaciones = new JLabel("# Simulaciones");
        lbNumeroSimulaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        lbNumeroSimulaciones.setBounds(35, lbTemperatura.getY() + 40, 150, 20);
        add(lbNumeroSimulaciones);
        
        // TextFields
        txtSensorID = new JTextField();
        txtSensorID.setBounds(150, lbSensorID.getY() -5 , 100, 30);
        add(txtSensorID);

        txtPresionArterial = new JTextField();
        txtPresionArterial.setBounds(txtSensorID.getX(), lbPresionArterial.getY() - 5, 100, 30);
        txtPresionArterial.setEditable(false);
        add(txtPresionArterial);
        
        txtPulso = new JTextField();
        txtPulso.setBounds(txtSensorID.getX(), lbPulso.getY() - 5, 100, 30);
        txtPulso.setEditable(false);
        add(txtPulso);
        
        txtTemperatura = new JTextField();
        txtTemperatura.setBounds(txtSensorID.getX(), lbTemperatura.getY() - 5, 100, 30);
        txtTemperatura.setEditable(false);
        add(txtTemperatura);
        
        txtNumeroSimulaciones = new JTextField();
        txtNumeroSimulaciones.setBounds(txtSensorID.getX(), lbNumeroSimulaciones.getY() - 5, 100, 30);
        add(txtNumeroSimulaciones);
        
        // Buttons
        btnIniciarSimulacion = new JButton("Iniciar Simulación");
        btnIniciarSimulacion.setBounds(50, 250, 200, 30);
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

    public JTextField getTxtPresionArterial() {
        return txtPresionArterial;
    }

    public JTextField getTxtTemperatura() {
        return txtTemperatura;
    }

    public JTextField getTxtNumeroSimulaciones() {
        return txtNumeroSimulaciones;
    }

    public JButton getBtnIniciarSimulacion() {
        return btnIniciarSimulacion;
    }
    
    
}

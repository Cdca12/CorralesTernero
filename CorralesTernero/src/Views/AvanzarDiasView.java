package Views;

import Controllers.AvanzarDiasController;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AvanzarDiasView extends JDialog {

    private AvanzarDiasController avanzarDiasController;
    private JLabel lbAvanzarDías;
    private JTextField txtDias;
    private JButton btnAvanzarDias;

    public AvanzarDiasView() {
        setTitle("Avanzar Días");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    public void initComponents() {
        // Labels
        lbAvanzarDías = new JLabel("Avanzar                       días");
        lbAvanzarDías.setFont(new Font("Arial", Font.PLAIN, 14));
        lbAvanzarDías.setBounds(65, 40, 300, 20);
        add(lbAvanzarDías);

        // TextFields
        txtDias = new JTextField();
        txtDias.setBounds(135, lbAvanzarDías.getY() - 5, 60, 30);
        add(txtDias);

        // Buttons
        btnAvanzarDias = new JButton("Simular");
        btnAvanzarDias.setBounds(50, 120, 200, 30);
        add(btnAvanzarDias);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(AvanzarDiasController avanzarDiasController) {
        this.avanzarDiasController = avanzarDiasController;
        addListeners();
    }

    private void addListeners() {
        btnAvanzarDias.addActionListener(avanzarDiasController);
    }

    public JTextField getTxtDias() {
        return txtDias;
    }

    public JButton getBtnAvanzarDias() {
        return btnAvanzarDias;
    }

    public void limpiarCampos() {
        txtDias.setText("");
        txtDias.requestFocus();
    }

}

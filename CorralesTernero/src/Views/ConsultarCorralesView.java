package Views;

import javax.swing.*;
import Controllers.*;
import java.util.Arrays;
import java.util.Vector;
import Utils.Tipo;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCorralesView extends JDialog {

    private ConsultarCorralesController corralesController;

    private JTable tablaCorrales;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;
    private Tipo config;

    public ConsultarCorralesView(Tipo config) {
        setTitle("Corrales");
        setSize(650, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        this.config = config;

        initComponents();
    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        int width = 590;
        if (config == Tipo.SELECCION) {
            btnSeleccionar = new JButton("Seleccionar");
            btnSeleccionar.setBounds(525, 160, 100, 30);
            btnSeleccionar.setEnabled(false);
            add(btnSeleccionar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.setBounds(btnSeleccionar.getX(), btnSeleccionar.getY() + 40, 100, 30);
            add(btnCancelar);

            width = 475; // Cambiar ancho tabla
        }
        scrollPane.setBounds(30, 30, width, 250);
        add(scrollPane);

    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ConsultarCorralesController corralesController) {
        this.corralesController = corralesController;
        generarTablaResultados();
        addListeners();
    }

    private void addListeners() {
        if (config == Tipo.SELECCION) {
            tablaCorrales.getSelectionModel().addListSelectionListener(corralesController);
            btnSeleccionar.addActionListener(corralesController);
            btnCancelar.addActionListener(corralesController);
        }
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCorrales = corralesController.obtenerDatosTabla(config);
        vectorNombreColumnas = new Vector<>(Arrays.asList("CorralID", "Estado", "Tipo"));
        tablaCorrales = new JTable(datosTablaCorrales, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCorrales);
    }

    public void guardarId() {
        String corralID = tablaCorrales.getValueAt(tablaCorrales.getSelectedRow(), 0).toString();
        AñadirCriaView.setCorralID(corralID);
        dispose();
    }

    public JTable getTablaCorrales() {
        return tablaCorrales;
    }

    public JButton getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

}

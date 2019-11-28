package Views;

import javax.swing.*;
import Controllers.*;
import java.util.Arrays;
import java.util.Vector;
import Utils.Config;

/**
 *
 * @author Carlos Contreras
 */
public class CorralesView extends JDialog {

    private CorralesController corralesController;

    private JTable tablaCorrales;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;
    private Config config;

    public CorralesView(Config config) {
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
        if (config == Config.SELECCION) {
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

    public void setController(CorralesController corralesController) {
        this.corralesController = corralesController;
        generarTablaResultados();
        addListeners();
    }

    private void addListeners() {
        tablaCorrales.getSelectionModel().addListSelectionListener(corralesController);
        if (config == Config.SELECCION) {
            btnSeleccionar.addActionListener(corralesController);
            btnCancelar.addActionListener(corralesController);
        }
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCorrales = corralesController.obtenerDatosTabla();
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

package Views;

import javax.swing.*;
import Controllers.*;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class DietasView extends JDialog {

    private DietasController dietasController;

    private JTable tablaDietas;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;

    public DietasView() {
        setTitle("Dietas");
        setSize(650, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();

    }

    private void initComponents() {
        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBounds(525, 160, 100, 30);
        btnSeleccionar.setEnabled(false);
        add(btnSeleccionar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(btnSeleccionar.getX(), btnSeleccionar.getY() + 40, 100, 30);
        add(btnCancelar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 475, 250);
        add(scrollPane);

    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(DietasController dietasController) {
        this.dietasController = dietasController;
        generarTablaResultados();
        addListeners();

    }

    private void addListeners() {
        tablaDietas.getSelectionModel().addListSelectionListener(evt -> {
            btnSeleccionar.setEnabled(true);
        });
        btnSeleccionar.addActionListener(evt -> {
            guardarId();
        });
        btnCancelar.addActionListener(evt -> {
            dispose();
        });
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaDietas = dietasController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("DietaID", "Nombre", "Cantidad", "Dias Tratamiento"));
        tablaDietas = new JTable(datosTablaDietas, vectorNombreColumnas);
        scrollPane.setViewportView(tablaDietas);
    }

    private void guardarId() {
        String DietaID = tablaDietas.getValueAt(tablaDietas.getSelectedRow(), 0).toString();
        AñadirCriaView.setDietaID(DietaID);
        dispose();
    }
}

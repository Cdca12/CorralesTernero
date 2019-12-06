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
public class ConsultarDietasView extends JDialog {

    private ConsultarDietasController dietasController;

    private JTable tablaDietas;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;
    private Tipo type;

    public ConsultarDietasView(Tipo type) {
        setTitle("Dietas");
        setSize(650, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        this.type = type;

        initComponents();

    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        int width = 590;
        if (type == Tipo.SELECCION) {
            btnSeleccionar = new JButton("Seleccionar");
            btnSeleccionar.setBounds(525, 160, 100, 30);
            btnSeleccionar.setEnabled(false);
            add(btnSeleccionar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.setBounds(btnSeleccionar.getX(), btnSeleccionar.getY() + 40, 100, 30);
            add(btnCancelar);

            width = 475;
        }
        scrollPane.setBounds(30, 30, width, 250);
        add(scrollPane);

    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ConsultarDietasController dietasController) {
        this.dietasController = dietasController;
        generarTablaResultados();
        addListeners();

    }

    private void addListeners() {
        if (type == Tipo.SELECCION) {
            tablaDietas.getSelectionModel().addListSelectionListener(dietasController);
            btnSeleccionar.addActionListener(dietasController);
            btnCancelar.addActionListener(dietasController);
        }
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaDietas = dietasController.obtenerDatosTabla(type);
        vectorNombreColumnas = new Vector<>(Arrays.asList("DietaID", "Nombre", "Cantidad", "Dias Tratamiento"));
        tablaDietas = new JTable(datosTablaDietas, vectorNombreColumnas);
        scrollPane.setViewportView(tablaDietas);
    }

    public void guardarId() {
        String DietaID = tablaDietas.getValueAt(tablaDietas.getSelectedRow(), 0).toString();
        AñadirCriaView.setDietaID(DietaID);
        dispose();
    }

    public JButton getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JTable getTablaDietas() {
        return tablaDietas;
    }

}

package Views;

import Controllers.ConsultarCriasController;
import Controllers.ConsultarCorralesController;
import Controllers.ConsultarDietasController;
import Utils.Status;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasView extends JDialog {

    private ConsultarCriasController consultarCriasController;

    private JTable tablaCrias;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JComboBox cmbFiltro;
    private JTextField txtConsultar;
    private JButton btnConsultar, btnConsultarTodas, btnImprimir;

    public ConsultarCriasView() {
        setTitle("Crias");
        setSize(1400, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        initComponents();

    }

    private void initComponents() {
        String[] filtros = {"CriaID", "CorralID", "Peso", "Grasa", "GrasaCobertura",
            "TipoMusculo", "SensorID", "DietaID", "EstadoCria", "DiasEdad"};
        cmbFiltro = new JComboBox(filtros);
        cmbFiltro.insertItemAt("Seleccione filtro", 0);
        cmbFiltro.setSelectedIndex(0);
        cmbFiltro.setBounds(35, 15, 185, 30);
        add(cmbFiltro);

        txtConsultar = new JTextField();
        txtConsultar.setBounds(cmbFiltro.getX() + cmbFiltro.getWidth() + 10, cmbFiltro.getY(), 150, 30);
        txtConsultar.setEditable(false);
        add(txtConsultar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(txtConsultar.getX() + txtConsultar.getWidth() + 10, txtConsultar.getY(), 100, 30);
        btnConsultar.setEnabled(false);
        add(btnConsultar);

        btnConsultarTodas = new JButton("Consultar todas");
        btnConsultarTodas.setBounds(btnConsultar.getX() + btnConsultar.getWidth() + 10, btnConsultar.getY(), 130, 30);
        add(btnConsultarTodas);

        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 125, btnConsultar.getY(), 100, 30);
        add(btnImprimir);

        generarTabla();
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ConsultarCriasController consultarCriasController) {
        this.consultarCriasController = consultarCriasController;
        addListeners();

    }

    private void addListeners() {
        btnConsultar.addActionListener(consultarCriasController);
        btnConsultarTodas.addActionListener(consultarCriasController);
        btnImprimir.addActionListener(consultarCriasController);
        cmbFiltro.addActionListener(consultarCriasController);
        txtConsultar.addKeyListener(consultarCriasController);
    }

    private void generarTabla() {
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "CriaID",
                "CorralID",
                "Peso",
                "Grasa",
                "GrasaCobertura",
                "TipoMusculo",
                "SensorID",
                "DietaID",
                "EstadoCria",
                "DiasEdad"
        ));
        tablaCrias = new JTable(null, vectorNombreColumnas);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 60, 1350, 480);
        add(scrollPane);
        scrollPane.setViewportView(tablaCrias);
    }

    public void generarDatosTabla(Vector<Vector<String>> datosTablaCrias) {
        tablaCrias = new JTable(datosTablaCrias, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCrias);
        tablaCrias.updateUI();

    }

    public JTable getTablaCrias() {
        return tablaCrias;
    }

    public JComboBox getCmbFiltro() {
        return cmbFiltro;
    }

    public JTextField getTxtConsultar() {
        return txtConsultar;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public JButton getBtnConsultarTodas() {
        return btnConsultarTodas;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaCrias.print()) {
                JOptionPane.showMessageDialog(null, Status.OK_PRINT.TITLE, Status.OK_PRINT.MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, Status.ERROR_PRINT.TITLE, Status.ERROR_PRINT.MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

}

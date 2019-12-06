package Views;

import Controllers.AnalizarSeñalesSensoresController;
import Utils.Status;
import java.awt.print.PrinterException;
import java.util.Arrays;
import javax.swing.*;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class AnalizarSeñalesSensoresView extends JDialog {

    private AnalizarSeñalesSensoresController analizarSeñalesSensoresController;

    private JTable tablaSeñalesSensores;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnCriasPropensasEnfermarse, btnAñadirCuarentena, btnAñadirCuarentenaAll, btnImprimir;

    public AnalizarSeñalesSensoresView() {
        setTitle("Analizar Señales Sensores");
        setSize(700, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        btnCriasPropensasEnfermarse = new JButton("Obtener crias propensas a enfermarse");
        btnCriasPropensasEnfermarse.setBounds(40, 430, 260, 30);
        add(btnCriasPropensasEnfermarse);

        btnAñadirCuarentenaAll = new JButton("Añadir todos a cuarentena");
        btnAñadirCuarentenaAll.setBounds(btnCriasPropensasEnfermarse.getX() + btnCriasPropensasEnfermarse.getWidth() + 10, 430, 180, 30);
        btnAñadirCuarentenaAll.setEnabled(false);
        add(btnAñadirCuarentenaAll);

        btnAñadirCuarentena = new JButton("Añadir a cuarentena");
        btnAñadirCuarentena.setBounds(btnAñadirCuarentenaAll.getX() + btnAñadirCuarentenaAll.getWidth() + 10, 430, 150, 30);
        btnAñadirCuarentena.setEnabled(false);
        add(btnAñadirCuarentena);

        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 150, 15, 100, 30);
        add(btnImprimir);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 60, 600, 350);
        add(scrollPane);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(AnalizarSeñalesSensoresController analizarSeñalesSensoresController) {
        this.analizarSeñalesSensoresController = analizarSeñalesSensoresController;
        generarTablaResultados();
        addListeners();
    }

    private void addListeners() {
        tablaSeñalesSensores.getSelectionModel().addListSelectionListener(analizarSeñalesSensoresController);
        btnCriasPropensasEnfermarse.addActionListener(analizarSeñalesSensoresController);
        btnAñadirCuarentenaAll.addActionListener(analizarSeñalesSensoresController);
        btnAñadirCuarentena.addActionListener(analizarSeñalesSensoresController);
        btnImprimir.addActionListener(analizarSeñalesSensoresController);
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaSeñalesSensores = analizarSeñalesSensoresController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("Señal", "SensorID", "PresionArterial", "Respiracion", "Pulso", "Temperatura"));
        tablaSeñalesSensores = new JTable(datosTablaSeñalesSensores, vectorNombreColumnas);
        scrollPane.setViewportView(tablaSeñalesSensores);
    }

    public void generarTablaPropensasEnfermarse() {
        Vector<Vector<String>> datosTablaPropensasEnfermarse = analizarSeñalesSensoresController.obtenerDatosTablaPropensosEnfermarse();
        vectorNombreColumnas = new Vector<>(Arrays.asList("Señal", "SensorID", "PresionArterial", "Respiracion", "Pulso", "Temperatura", "CriaID"));
        tablaSeñalesSensores = new JTable(datosTablaPropensasEnfermarse, vectorNombreColumnas);
        tablaSeñalesSensores.getSelectionModel().addListSelectionListener(analizarSeñalesSensoresController);
        scrollPane.setViewportView(tablaSeñalesSensores);
        if (tablaSeñalesSensores.getRowCount() > 0) {
            btnAñadirCuarentenaAll.setEnabled(true);
        }
    }

    public void actualizarTablaPropensosEnfermarse() {
        generarTablaPropensasEnfermarse();
        scrollPane.updateUI();
    }

    public JButton getBtnCriasPropensasEnfermarse() {
        return btnCriasPropensasEnfermarse;
    }

    public JButton getBtnAñadirCuarentena() {
        return btnAñadirCuarentena;
    }

    public JButton getBtnAñadirCuarentenaAll() {
        return btnAñadirCuarentenaAll;
    }

    public JTable getTablaSeñalesSensores() {
        return tablaSeñalesSensores;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaSeñalesSensores.print()) {
                JOptionPane.showMessageDialog(null, Status.OK_PRINT.TITLE, Status.OK_PRINT.MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, Status.ERROR_PRINT.TITLE, Status.ERROR_PRINT.MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showErrorMessage(String ERROR_MESSAGE, String ERROR_TITLE) {
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void showOkMessage(String OK_MESSAGE, String OK_TITLE) {
        JOptionPane.showMessageDialog(null, OK_MESSAGE, OK_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

}

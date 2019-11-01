package Views;

import Controllers.AnalizarSeñalesSensoresController;
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
    private JButton btnAñadirCuarentena;
    
    public AnalizarSeñalesSensoresView() {
        setTitle("Analizar Señales Sensores");
        setSize(650, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        btnAñadirCuarentena = new JButton("Añadir a cuarentena");
        btnAñadirCuarentena.setBounds(250, 430, 150, 30);
        add(btnAñadirCuarentena);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 60, 550, 350);
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
        btnAñadirCuarentena.addActionListener(analizarSeñalesSensoresController);
        
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaSeñalesSensores = analizarSeñalesSensoresController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("Señal", "SensorID", "PresionArterial", "Respiracion", "Pulso", "Temperatura"));
        tablaSeñalesSensores = new JTable(datosTablaSeñalesSensores, vectorNombreColumnas);
        scrollPane.setViewportView(tablaSeñalesSensores);
    }

    public JTable getTablaSeñalesSensores() {
        return tablaSeñalesSensores;
    }

    public JButton getBtnAñadirCuarentena() {
        return btnAñadirCuarentena;
    }
    
    
    
}

package Views;

import javax.swing.*;
import Controllers.*;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class AlimentosView extends JDialog {
    
    private AlimentosController alimentosController;

    private JTable tablaAlimentos;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;
    
    public AlimentosView() {
        setTitle("Corrales");
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

    public void setController(AlimentosController alimentosController) {
        this.alimentosController = alimentosController;
        generarTablaResultados();
        addListeners();
    }
    
    private void addListeners() {
        tablaAlimentos.getSelectionModel().addListSelectionListener(alimentosController);
        btnSeleccionar.addActionListener(alimentosController);
        btnCancelar.addActionListener(alimentosController);
    }
    
    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaAlimentos = alimentosController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("AlimentoID", "Nombre", "Cantidad"));
        tablaAlimentos = new JTable(datosTablaAlimentos, vectorNombreColumnas);
        scrollPane.setViewportView(tablaAlimentos);
    }
    
    public void guardarId() {
        String alimentolID = tablaAlimentos.getValueAt(tablaAlimentos.getSelectedRow(), 0).toString();
        AñadirDietasView.setAlimentoID(alimentolID);
        AñadirDietasView.getTxtDiasTratamiento().requestFocus();
        dispose();
    }

    public JTable getTablaAlimentos() {
        return tablaAlimentos;
    }

    public JButton getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    
    
}
package Views;

import Controllers.*;
import Controllers.ConsultarCriasASacrificarController;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarCriasASacrificarView extends JDialog {
    
    private ConsultarCriasASacrificarController consultarCriasASacrificarController;
    
    private JTable tablaCriasASacrificar;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    
    private JButton btnSacrificar, btnSacrificarAll;
    
    public ConsultarCriasASacrificarView() {
        setTitle("Consultar Crias A Sacrificar");
        setSize(680, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        
        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 600, 380);
        add(scrollPane);
        
        btnSacrificarAll = new JButton("Sacrificar todos");
        btnSacrificarAll.setBounds(220, 420, 120, 30);
        btnSacrificarAll.setEnabled(false);
        add(btnSacrificarAll);
        
        btnSacrificar = new JButton("Sacrificar");
        btnSacrificar.setBounds(btnSacrificarAll.getX() + 130, btnSacrificarAll.getY(), 100, 30);
        btnSacrificar.setEnabled(false);
        add(btnSacrificar);
        
    }
    
    public void launchView() {
        setVisible(true);
    }
    
    public void setController(ConsultarCriasASacrificarController consultarCriasASacrificarController) {
        this.consultarCriasASacrificarController = consultarCriasASacrificarController;
        generarTablaResultados();
        addListeners();
    }
    
    private void addListeners() {
        tablaCriasASacrificar.getSelectionModel().addListSelectionListener(consultarCriasASacrificarController);
        btnSacrificar.addActionListener(consultarCriasASacrificarController);
        btnSacrificarAll.addActionListener(consultarCriasASacrificarController);
    }
    
    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCriasASacrificar = consultarCriasASacrificarController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "Transaccion",
                "CorralID",
                "CriaID",
                "FechaIngreso",
                "FechaEgreso",
                "DiasEnCorral"
        ));
        tablaCriasASacrificar = new JTable(datosTablaCriasASacrificar, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCriasASacrificar);
        if (tablaCriasASacrificar.getRowCount() > 0) {
            btnSacrificarAll.setEnabled(true);
        }
    }
    
    public JTable getTablaCriasASacrificar() {
        return tablaCriasASacrificar;
    }
    
    public JButton getBtnSacrificar() {
        return btnSacrificar;
    }
    
    public JButton getBtnSacrificarAll() {
        return btnSacrificarAll;
    }
    
}

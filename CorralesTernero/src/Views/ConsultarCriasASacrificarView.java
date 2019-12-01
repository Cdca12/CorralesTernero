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
    
    private JButton btnSacrificar, btnCancelar;
    
    public ConsultarCriasASacrificarView() {
        setTitle("Consultar Crias A Sacrificar");
        setSize(750, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 600, 400);
        add(scrollPane);
        
        btnSacrificar = new JButton("Sacrificar");
        btnSacrificar.setBounds(640, 300, 100, 30);
        add(btnSacrificar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(btnSacrificar.getX(), btnSacrificar.getY() + 40, 100, 30);
        add(btnCancelar);
        
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
        btnCancelar.addActionListener(consultarCriasASacrificarController);
    }
    
    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaCriasASacrificar = consultarCriasASacrificarController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "Transaccion",
                "CorralID",
                "CriaID",
                "FechaIngreso",
                "FechaEgreso",
                "DiaesEnCorral"
        ));
        tablaCriasASacrificar= new JTable(datosTablaCriasASacrificar, vectorNombreColumnas);
        scrollPane.setViewportView(tablaCriasASacrificar);
    }

    public JTable getTablaCriasASacrificar() {
        return tablaCriasASacrificar;
    }
    
    public JButton getBtnSacrificar() {
        return btnSacrificar;
    }
    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
}

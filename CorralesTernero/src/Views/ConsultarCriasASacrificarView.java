package Views;

import Controllers.*;
import Controllers.ConsultarCriasASacrificarController;
import Utils.Status;
import java.awt.print.PrinterException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
    
    private JButton btnSacrificar, btnSacrificarAll, btnImprimir;
    
    public ConsultarCriasASacrificarView() {
        setTitle("Consultar Crias A Sacrificar");
        setSize(680, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        
        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 60, 600, 400);
        add(scrollPane);
        
        btnSacrificarAll = new JButton("Sacrificar todos");
        btnSacrificarAll.setBounds(220, 470, 120, 30);
        btnSacrificarAll.setEnabled(false);
        add(btnSacrificarAll);
        
        btnSacrificar = new JButton("Sacrificar");
        btnSacrificar.setBounds(btnSacrificarAll.getX() + 130, btnSacrificarAll.getY(), 100, 30);
        btnSacrificar.setEnabled(false);
        add(btnSacrificar);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 150, 15, 100, 30);
        add(btnImprimir);
        
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
        btnImprimir.addActionListener(consultarCriasASacrificarController);
    }
    
    public void generarTablaResultados() {
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

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaCriasASacrificar.print()) {
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

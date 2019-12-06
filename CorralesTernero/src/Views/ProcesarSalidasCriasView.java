package Views;

import Controllers.ProcesarSalidasCriasController;
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
public class ProcesarSalidasCriasView extends JDialog {

    private ProcesarSalidasCriasController procesarSalidasCriasController;

    private JTable tablaProcesarSalidasCrias;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnProcesar, btnProcesarAll, btnImprimir;

    public ProcesarSalidasCriasView() {
        setTitle("Procesar Salidas");
        setSize(1120, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        btnProcesarAll = new JButton("Procesar todas");
        btnProcesarAll.setBounds(430, 520, 130, 30);
        btnProcesarAll.setEnabled(false);
        add(btnProcesarAll);
        
        btnProcesar = new JButton("Procesar cría");
        btnProcesar.setBounds(btnProcesarAll.getX() + 140, btnProcesarAll.getY(), 130, 30);
        btnProcesar.setEnabled(false);
        add(btnProcesar);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(getWidth() - 140, 15, 100, 30);
        add(btnImprimir);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 60, 1050, 440);
        add(scrollPane);
    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ProcesarSalidasCriasController procesarSalidasCriasController) {
        this.procesarSalidasCriasController = procesarSalidasCriasController;
        generarTablaResultados();
        addListeners();
    }

    private void addListeners() {
        tablaProcesarSalidasCrias.getSelectionModel().addListSelectionListener(procesarSalidasCriasController);
        btnProcesar.addActionListener(procesarSalidasCriasController);
        btnProcesarAll.addActionListener(procesarSalidasCriasController);
        btnImprimir.addActionListener(procesarSalidasCriasController);
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaProcesarSalidasCrias = procesarSalidasCriasController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "CriaID",
                "CorralID",
                "Peso",
                "Grasa",
                "GrasaCobertura",
                "TipoMusculo",
                "EstadoCria",
                "DiasEdad"
        ));
        tablaProcesarSalidasCrias = new JTable(datosTablaProcesarSalidasCrias, vectorNombreColumnas);
        scrollPane.setViewportView(tablaProcesarSalidasCrias);
        if (tablaProcesarSalidasCrias.getRowCount() > 0) {
            btnProcesarAll.setEnabled(true);
        }
        
    }

    public JTable getTablaProcesarSalidasCrias() {
        return tablaProcesarSalidasCrias;
    }

    public JButton getBtnProcesar() {
        return btnProcesar;
    }

    public JButton getBtnProcesarAll() {
        return btnProcesarAll;
    }
    
    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void imprimirTabla() {
        try {
            if (tablaProcesarSalidasCrias.print()) {
                JOptionPane.showMessageDialog(null, Status.OK_PRINT.TITLE, Status.OK_PRINT.MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, Status.ERROR_PRINT.TITLE, Status.ERROR_PRINT.MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

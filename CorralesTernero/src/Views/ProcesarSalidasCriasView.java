package Views;

import Controllers.ConsultarCorralesController;
import Controllers.ProcesarSalidasCriasController;
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
public class ProcesarSalidasCriasView extends JDialog {

    private ProcesarSalidasCriasController procesarSalidasCriasController;

    private JTable tablaProcesarSalidasCrias;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;

    private JButton btnProcesar, btnProcesarAll;

    public ProcesarSalidasCriasView() {
        setTitle("Procesar Salidas");
        setSize(1120, 390);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        btnProcesarAll = new JButton("Procesar todas");
        btnProcesarAll.setBounds(430, 300, 130, 30);
        add(btnProcesarAll);
        
        btnProcesar = new JButton("Procesar cría");
        btnProcesar.setBounds(btnProcesarAll.getX() + 140, btnProcesarAll.getY(), 130, 30);
        btnProcesar.setEnabled(false);
        add(btnProcesar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 1050, 250);
        add(scrollPane);

    }

    public void launchView() {
        setVisible(true);
    }

    public void setController(ProcesarSalidasCriasController procesarSalidasCriasController) {
        this.procesarSalidasCriasController = procesarSalidasCriasController;
        generarTablaResultados();
        validarBotonProcesar();
        addListeners();
    }

    private void addListeners() {
        tablaProcesarSalidasCrias.getSelectionModel().addListSelectionListener(procesarSalidasCriasController);
        btnProcesar.addActionListener(procesarSalidasCriasController);
        btnProcesarAll.addActionListener(procesarSalidasCriasController);
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
    
    private void validarBotonProcesar() {
        if (tablaProcesarSalidasCrias.getRowCount() == 0) {
            btnProcesar.setEnabled(false);
            btnProcesarAll.setEnabled(false);
            return;
        }
    }
}

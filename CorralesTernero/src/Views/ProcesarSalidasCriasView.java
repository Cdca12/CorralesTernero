package Views;

import Controllers.CorralesController;
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

    private JButton btnProcesar, btnCancelar;

    public ProcesarSalidasCriasView() {
        setTitle("Procesar Salidas");
        setSize(1200, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        btnProcesar = new JButton("Procesar");
        btnProcesar.setBounds(1035, 160, 100, 30);
//        btnProcesar.setEnabled(false);
        // TODO: Habilitar si hay crias disponibles, si no, mantener deshabilitado
        add(btnProcesar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(btnProcesar.getX(), btnProcesar.getY() + 40, 100, 30);
        add(btnCancelar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 950, 250);
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
        btnCancelar.addActionListener(procesarSalidasCriasController);
    }

    private void generarTablaResultados() {
        Vector<Vector<String>> datosTablaProcesarSalidasCrias = procesarSalidasCriasController.obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList(
                "CriasID",
                "CorralID",
                "Peso",
                "GrasaCobertura",
                "TipoMusculo",
                "EstadoCria",
                "DiasEnCorral"
        ));
        tablaProcesarSalidasCrias = new JTable(datosTablaProcesarSalidasCrias, vectorNombreColumnas);
        scrollPane.setViewportView(tablaProcesarSalidasCrias);
    }

    public void procesarSalidas() {
        // Nada aun...
    }

    public JTable getTablaProcesarSalidasCrias() {
        return tablaProcesarSalidasCrias;
    }

    public JButton getBtnProcesar() {
        return btnProcesar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

}

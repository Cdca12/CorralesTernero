package Views;

import DataAccesor.SQLConnectionHelper;
import Entities.Corrales;
import Views.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class DietasView extends JDialog {

    private JTable tablaDietas;
    private JScrollPane scrollPane;
    private Vector<String> vectorNombreColumnas;
    private JButton btnSeleccionar, btnCancelar;

    public DietasView() {
        setTitle("Dietas");
        setSize(650, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        initComponents();
    }

    private void initComponents() {
        generarTablaResultados();
        
        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBounds(525, 160, 100, 30);
        btnSeleccionar.setEnabled(false);
        add(btnSeleccionar);
        
        tablaDietas.getSelectionModel().addListSelectionListener(evt -> {
            btnSeleccionar.setEnabled(true);
        });
        
        
        btnSeleccionar.addActionListener(evt -> {
            guardarId();
        });
        
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(btnSeleccionar.getX(), btnSeleccionar.getY() + 40, 100, 30);
        
        // TODO: Acomodar arquitectura
        btnCancelar.addActionListener(evt -> {
            dispose();
        });
        
        add(btnCancelar);

        scrollPane = new JScrollPane(tablaDietas);
        scrollPane.setBounds(30, 30, 475, 250);
        add(scrollPane);
        
        

    }

    public void launchView() {
        setVisible(true);
    }

    private void generarTablaResultados() {
        // TODO: Mandar a llamar Modelo de la pantalla, y este mandar a llamar a Modelo de Dietas
        Vector<Vector<String>> datosTablaDietas = obtenerDatosTabla();
        vectorNombreColumnas = new Vector<>(Arrays.asList("DietaID", "Nombre", "Cantidad", "Dias Tratamiento"));
        tablaDietas = new JTable(datosTablaDietas, vectorNombreColumnas);
    }

    // Este método va en el Model de Dietas
    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaDietas = new Vector<>();
        
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT D.DietaID, A.Nombre, A.Cantidad, D.DiasTratamiento FROM Dietas D "
                    + "INNER JOIN Alimentos A "
                    + "ON D.AlimentoID = A.AlimentoID;");
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                row.add(resultQuery.getString(4));
                datosTablaDietas.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaDietas;
    }
    
    private void guardarId() {
//        String DietaID = "0";
        String DietaID = tablaDietas.getValueAt(tablaDietas.getSelectedRow(), 0).toString();
        AñadirCriaView.getTxtDieta().setText(DietaID);
        dispose();
    }
}

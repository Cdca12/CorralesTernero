package Views;

import Controllers.AñadirCriaController;
import javax.swing.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    private AñadirCriaController añadirCriaController;

    public AñadirCriaView() {
        setTitle("Añadir Cría");
        setSize(600, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        
        initComponents();
    }
    
    private void initComponents() {
        
    }
    
    public void launchView() {
        setVisible(true);
    }
    
    

    public void setController(AñadirCriaController añadirCriaController) {
        this.añadirCriaController = añadirCriaController;
        addListeners();
    }

    private void addListeners() {
        // Añadir escuchadores aqui con lambda o estrictamente en Controller?
        
    }

}

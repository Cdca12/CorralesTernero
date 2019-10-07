package Views;

import javax.swing.*;
import Controller.*;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaView extends JDialog {

    private AñadirCriaController añadirCriaController;

    public AñadirCriaView() {
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

    }

}

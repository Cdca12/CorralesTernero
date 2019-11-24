package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class SeñalesSensores {
    
    private String Señal;
    private String SensorID;
    private String PresionArterial;
    private String Respiracion;
    private String Pulso;
    private String Temperatura;

    public String getSeñal() {
        return Señal;
    }

    public void setSeñal(String Señal) {
        this.Señal = Señal;
    }

    public String getSensorID() {
        return SensorID;
    }

    public void setSensorID(String SensorID) {
        this.SensorID = SensorID;
    }

    public String getPresionArterial() {
        return PresionArterial;
    }

    public void setPresionArterial(String PresionArterial) {
        this.PresionArterial = PresionArterial;
    }

    public String getRespiracion() {
        return Respiracion;
    }

    public void setRespiracion(String Respiracion) {
        this.Respiracion = Respiracion;
    }

    public String getPulso() {
        return Pulso;
    }

    public void setPulso(String Pulso) {
        this.Pulso = Pulso;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String Temperatura) {
        this.Temperatura = Temperatura;
    }
        
    // Métodos CRUD
    
}

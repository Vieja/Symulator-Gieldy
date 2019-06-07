/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vieja
 */
public abstract class Rynek {
    private String nazwa;
    private String kraj;
    private String waluta;
    private String miasto;
    private String adres;
    private Double marza;
    

    public StringProperty nazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
    public String getNazwa(){
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public abstract String getTyp();
    public abstract StringProperty typProperty();
    
    public StringProperty krajProperty() {
        return new SimpleStringProperty(kraj);
    }
    public String getKraj(){
        return kraj;
    }
    public void setKraj(String kraj) {
        this.kraj = kraj;
    }
    
    public StringProperty walutaProperty() {
        return new SimpleStringProperty(waluta);
    }
    public String getWaluta(){
        return waluta;
    }
    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }
    
    public StringProperty miastoProperty() {
        return new SimpleStringProperty(miasto);
    }
    public String getMiasto(){
        return miasto;
    }
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
    
    public StringProperty adresProperty() {
        return new SimpleStringProperty(adres);
    }
    public String getAdres(){
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    public DoubleProperty marzaProperty() {
        return new SimpleDoubleProperty(marza);
    }
    public Double getMarza(){
        return marza;
    }
    public void setMarza(Double marza) {
        this.marza = marza;
    }
    
    public Rynek getRynek() {
        return this;
    }
}

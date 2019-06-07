/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vieja
 */
public class Indeks {
    private String nazwa;
    private String typ;
    private Integer wig;
    private Gielda gielda;
    ArrayList<Akcja> listaSpolek = new ArrayList<Akcja>();
    
    public Indeks(){
        this.listaSpolek.clear();
    }
    
    public StringProperty nazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
    public String getNazwa(){
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public StringProperty typProperty() {
        return new SimpleStringProperty(typ);
    }
    public String getTyp(){
        return typ;
    }
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public IntegerProperty wigProperty() {
        return new SimpleIntegerProperty(wig);
    }
    public Integer getWig(){
        return wig;
    }
    public void setWig(Integer wig) {
        this.wig = wig;
    }
    
    public StringProperty nazwaGieldaProperty() {
        return new SimpleStringProperty(this.gielda.getNazwa());
    }
    public Gielda getGielda(){
        return gielda;
    }
    public void setGielda(Gielda gielda) {
        this.gielda = gielda;
    }
    
    public ArrayList getListaSpolek(){
        return listaSpolek;
    }
    public void addListaSpolek(Akcja akcja) {
        this.listaSpolek.add(akcja);
    }
    public Akcja getSpolka(Integer i){
        return this.listaSpolek.get(i);
    }
    public Boolean sprawdzCzyNowa(Akcja akcja){
        Akcja moja;
        for (int i = 0; i < this.listaSpolek.size(); i++) {
            moja = this.listaSpolek.get(i);
            if (moja == akcja) return false;
        }
        return true;
    } 

    public StringProperty typGieldaProperty() {
        return new SimpleStringProperty(typ);
    }
    
    
    
}

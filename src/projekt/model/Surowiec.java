/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vieja
 */
public class Surowiec extends Aktywa{
    private String jednostka_handlowa;
    private String waluta;
    private double min_zanotowana;
    private double max_zanotowana;
    
    public Surowiec(){
    }

    @Override
    public String getTyp() {
        return "surowiec";
    }
    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("surowiec");
    }
    
    public String getJednostka(){
        return jednostka_handlowa;
    }
    public void setJednostka(String jednostka) {
        this.jednostka_handlowa = jednostka;
    }

    public String getWaluta(){
        return waluta;
    }
    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }
    
    public Double getMin(){
        return min_zanotowana;
    }
    public void setMin(Double min) {
        this.min_zanotowana = min;
    }

    public Double getMax(){
        return max_zanotowana;
    }
    public void setMax(Double max) {
        this.max_zanotowana = max;
    }    

    @Override
    public void setMinMax(Double temp) {
        if (temp>this.getMax()) this.setMax(temp);
        if (temp<this.getMin()) this.setMin(temp);
    }
}

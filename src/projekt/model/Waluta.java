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
public class Waluta extends Aktywa{
    
    public Waluta(){

    }
    
    @Override
    public String getTyp() {
        return "waluta";
    }
    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("waluta");
    }

    @Override
    public void setMinMax(Double temp) {
    }
}

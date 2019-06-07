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
public class RynekSurowcow  extends Rynek{

    public RynekSurowcow(){
    }
    
    @Override
    public String getTyp() {
        return "rynek surowców";
    }

    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("rynek surowców");   
    }
    
}

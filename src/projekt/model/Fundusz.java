/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Vieja
 */
public class Fundusz extends Inwestor implements Runnable{
    private String nazwisko;
    
    public Fundusz() {
        this.posiadaneAktywa.clear();
        this.posiadaneJednostki.clear();
    }
    
    @Override
    public void run() {
        while(true) {
            System.out.println(this.getNazwa() + " działam jako Fundusz");
            Random generator = new Random();
            for (int i=0;i<1;i++) {
                int los = generator.nextInt(5);
                if (this.getAktywo()!=null) {
                    if (los < 2 || posiadaneAktywa.size() == 0) //KUP AKTYWO
                        this.kupAktywo();
                    else if (los < 4 && posiadaneAktywa.size() != 0)
                        this.sprzedajAktywo();
                }
            }
            this.eatPizza();
            this.setPizzaArrived(false);
        }    
    }
    
    @Override
    public String getTyp() {
        return "fundusz";
    }
    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("fundusz");   
    }
    
    public String getNazwisko(){
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public void losujFundusz(ObservableList<Fundusz> listaFundusz) {
    }
    
}

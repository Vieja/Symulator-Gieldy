/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Vieja
 */
public abstract class Inwestor{
    private String imie;
    private String nazwa;
    private Double budzet;
    ObservableList<Aktywa> posiadaneAktywa = FXCollections.observableArrayList();
    ObservableList<Fundusz> posiadaneJednostki = FXCollections.observableArrayList();
    
    private boolean pizzaArrived = false;
    private Aktywa aktywo;
    
    
    public String getImie(){
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
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
    
    public ObservableList<Fundusz> getlistaJednostek() {        
        return posiadaneJednostki;
    }
    
    public abstract String getTyp();
    public abstract StringProperty typProperty();
    
    public void eatPizza(){
        synchronized(this){
            while(!pizzaArrived){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(InwestorIndywidualny.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void pizzaGuy(){
        synchronized(this){
             this.pizzaArrived = true;
             notifyAll();
        }
    }
    
    public Boolean getPizzaArrived(){
        return pizzaArrived;
    }
    public void setPizzaArrived(Boolean pizza) {
        this.pizzaArrived = pizza;
    }
    
    public DoubleProperty budzetProperty() {
        return new SimpleDoubleProperty(budzet);
    }
    
    public Double getBudzet(){
        return budzet;
    }
    public void setBudzet(Double budzet) {
        this.budzet = budzet;
    }
    
    public Aktywa getAktywo(){
        return aktywo;
    }
    public ObservableList<Aktywa> getlistaAktywa() {
        return posiadaneAktywa;
    }

    public void losujAktywo(ObservableList<Aktywa> listaAktywa) {
         Random generator = new Random();
         int los = generator.nextInt(listaAktywa.size());
         this.aktywo = listaAktywa.get(los);
    }
    
    public abstract void losujFundusz(ObservableList<Fundusz> listaFundusz);
    
    public void kupAktywo() {
        Double cena = aktywo.getKurs();
        if (cena < this.getBudzet()) {
            posiadaneAktywa.add(aktywo);
            this.setBudzet(round(this.getBudzet()-aktywo.getKurs()-aktywo.getKurs()*aktywo.getRynek().getMarza()/100));
            aktywo.zmienKurs(0);
            aktywo.setCzyKtosCos(true);
        }
    }
    
    public void sprzedajAktywo() {
        Random generator = new Random();
        int los = generator.nextInt(posiadaneAktywa.size());
        this.setBudzet(round(this.getBudzet()+posiadaneAktywa.get(los).getKurs()-posiadaneAktywa.get(los).getKurs()*aktywo.getRynek().getMarza()/100));
        posiadaneAktywa.remove(posiadaneAktywa.get(los));
        aktywo.zmienKurs(1);
        aktywo.setCzyKtosCos(true);
    }
    
    public static double round(double f)
   {  double temp = (double)(f*(Math.pow(10,2)));
          temp = (Math.round(temp));
          temp = temp/(int)(Math.pow(10,2));
          return temp;

   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vieja
 */
public class Akcja extends Aktywa implements Runnable{
    
    private String data;
    private Double kurs_otwarcia;
    private Double kurs_min;
    private Double kurs_max;
    private Integer liczba_akcji;
    private Double zysk;
    private Double przychod;
    private Double kapital_wlasny;
    private Double kapital_zakladowy;
    private Integer wolumen;
    private Double obroty;
    
    private Boolean pizzaArrived = false;

    
    @Override
    public void run() {
        while(true) { 
            Random generator = new Random();
            Double liczba = round (generator.nextInt(2000) + generator.nextDouble());
            this.zysk = liczba;
            liczba = round (liczba+generator.nextInt(2000) + generator.nextDouble());
            this.przychod = liczba;
            this.eatPizza();
            this.setPizzaArrived(false);
        }
    }
    
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
    
    @Override
    public String getTyp() {
        return "akcja";
    }
    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("akcja");
    }
    
    public StringProperty dataProperty() {
        return new SimpleStringProperty(data);
    }
    public String getData(){
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
    public Double getKursOtwarcia(){
        return kurs_otwarcia;
    }
    public void setKursOtwarcia(Double kurs) {
        this.kurs_otwarcia = kurs;
    }
    
    public Double getMin(){
        return kurs_min;
    }
    public void setMin(Double kurs) {
        this.kurs_min = kurs;
    }
    
    public Double getMax(){
        return kurs_max;
    }
    public void setMax(Double kurs) {
        this.kurs_max = kurs;
    }
    
    public Integer getLiczbaAkcji(){
        return liczba_akcji;
    }
    public void setliczbaAkcji(Integer liczba) {
        this.liczba_akcji = liczba;
    }
    
    public Double getZysk(){
        return zysk;
    }
    public void setZysk(Double zysk) {
        this.zysk = zysk;
    }
    
    public Double getPrzychod(){
        return przychod;
    }
    public void setPrzychod(Double kurs) {
        this.przychod = kurs;
    }
    
    public Double getKapitalWlasny(){
        return kapital_wlasny;
    }
    public void setKapitalWlasny(Double kurs) {
        this.kapital_wlasny = kurs;
    }
    
    public Double getKapitalZakladowy(){
        return kapital_zakladowy;
    }
    public void setKapitalZakladowy(Double kurs) {
        this.kapital_zakladowy = kurs;
    }
    
    public Integer getWolumen(){
        return wolumen;
    }
    public void setWolumen(Integer kurs) {
        this.wolumen = kurs;
    }
    
    public StringProperty obrotyProperty() {
        return new SimpleStringProperty(Double.toString(obroty));
    }
    public Double getObroty(){
        return obroty;
    }
    public void setObroty(Double kurs) {
        this.obroty = round(kurs);
    }

    @Override
    public void setMinMax(Double temp) {
        if (temp>this.getMax()) this.setMax(temp);
        if (temp<this.getMin()) this.setMin(temp);
    }
    public static double round(double f)
   {  double temp = (double)(f*(Math.pow(10,2)));
          temp = (Math.round(temp));
          temp = temp/(int)(Math.pow(10,2));
          return temp;

   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.model;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vieja
 */
public abstract class Aktywa {
    private String nazwa;
    private Double kurs;
    private Rynek rynek;
    private ArrayList<Double> historia = new ArrayList<Double>(10);
    private ArrayList<Double> procenty = new ArrayList<Double>(10);
    
    private Boolean czyKtosCos = false;
    private Double tempKurs = kurs;
    
    public abstract String getTyp();
    public abstract StringProperty typProperty();
    
    public StringProperty nazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
    public String getNazwa(){
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public DoubleProperty kursProperty() {
        return new SimpleDoubleProperty(kurs);
    }
    public Double getKurs(){
        return kurs;
    }
    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }
    
    public StringProperty rynekProperty(){
        return this.rynek.nazwaProperty();
    }
    public String getNazwaRynek() {
        return this.rynek.getNazwa();
    }
    
    public Rynek getRynek() {
        return rynek;
    }
    public void setRynek(Rynek rynek) {
        this.rynek = rynek;
    }
    
    public Aktywa getAktywo() {
        return this;
    }
    
    public Boolean getCzyKtosCos() {
        return czyKtosCos;
    }
    
    public void setCzyKtosCos(Boolean czyKtosCos) {
        this.czyKtosCos = czyKtosCos;
    }
    
      public void zmienKurs(Integer jak) {
        Random generator = new Random();
        if (jak == 0) { //KUPIONY, ZYSKUJE WARTOŚĆ
            tempKurs = round(kurs + kurs * round(generator.nextInt(14)+1 + generator.nextDouble())/100);
          
        } else if (jak == 1) {  //SPRZEDANY
            tempKurs = round(kurs - kurs * round(generator.nextInt(14)+1 + generator.nextDouble())/100);
        }
        if (this.getClass() == Akcja.class) {
            Akcja akcja = (Akcja) this;
            akcja.setWolumen(akcja.getWolumen()+1);
            akcja.setObroty(akcja.getObroty()+kurs);
        }
      }
      
    public void koniecDnia() {
        if (czyKtosCos == true) {
            this.dodajHistoria(kurs);
            this.setKurs(tempKurs);
            this.setMinMax(tempKurs);
        } else {
            this.dodajHistoria(kurs);
        }
        czyKtosCos = false;
    }
    
    public abstract void setMinMax(Double temp);
    
    public void dodajHistoria(Double kurs) {
        Double temp = kurs;
        Double temp2;
        for (int i=0;i<10;i++) {
            temp2 = historia.get(i);
            historia.remove(i);
            historia.add(i, temp);
            temp = temp2;
        }
        double dzielenie = historia.get(0)/historia.get(1);
        if (dzielenie >1) {
            temp = (dzielenie-1)*100;
        } else {
            temp = -1*(1-dzielenie)*100;
        }
        for (int i=0;i<10;i++) {
            temp2 = procenty.get(i);
            procenty.remove(i);
            procenty.add(i, temp);
            temp = temp2;
            
        }
    }
    public void czyscHistoria() {
        historia.clear();
        for (int i=0;i<10;i++) {
            historia.add(i, kurs);
            procenty.add(i, 0.0);
        }
    }
    public Double getHistoria(Integer id) {
        return historia.get(id);
    }
    
    public Double getProcenty(Integer id) {
        return procenty.get(id);
    }
    
    public static double round(double f)
   {  double temp = (double)(f*(Math.pow(10,2)));
          temp = (Math.round(temp));
          temp = temp/(int)(Math.pow(10,2));
          return temp;

   }
}

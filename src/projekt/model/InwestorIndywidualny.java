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
public class InwestorIndywidualny extends Inwestor implements Runnable{
    private Integer pesel;
    
    private Fundusz fundusz;
    
    public InwestorIndywidualny() {
        this.posiadaneAktywa.clear();
        this.posiadaneJednostki.clear();
    
    }
    @Override
    public void run() {
        while(true) {
            System.out.println(this.getNazwa() + " działam");
            Random generator = new Random();
            for (int i=0;i<3;i++) {    
                int los = generator.nextInt(13);
                if (this.getAktywo()!=null) {
                    if (los < 5 || posiadaneAktywa.size() == 0) //KUP AKTYWO
                        this.kupAktywo();
                    else if (los < 10 && posiadaneAktywa.size() != 0) //SPRZEDAJ AKTYWO
                        this.sprzedajAktywo();
                    else if (los == 10 && fundusz!=null) //KUP JEDNOSTKE UCZESTNICTWA
                        this.kupJednostke();
                }     
            }    
            this.eatPizza();
            this.setPizzaArrived(false);
        }    
    }
    @Override  
    public void losujFundusz(ObservableList<Fundusz> listaFundusz) {
         Random generator = new Random();
         String typ;
         int los;
         do {
                los = generator.nextInt(listaFundusz.size());
                typ = listaFundusz.get(los).getTyp(); 
         } while (typ == "indywidualny" );
         this.fundusz = listaFundusz.get(los);
    }
    
    public void kupJednostke() {
        Double cena = fundusz.getBudzet()/100;
         if (cena < this.getBudzet()) {
            posiadaneJednostki.add(fundusz);
            this.setBudzet(round(this.getBudzet()-cena));
        }
    }    
    
    public ObservableList<Fundusz> getlistaJednostek() {
        return posiadaneJednostki;
    }
    
    @Override
    public String getTyp() {
        return "indywidualny";
    }
    @Override
    public StringProperty typProperty() {
        return new SimpleStringProperty("indywidualny");   
    }
    
    public Integer getPesel(){
        return pesel;
    }
    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }
    

    
    
    
}

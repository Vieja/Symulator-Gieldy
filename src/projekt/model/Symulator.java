/*
 * Klasa - wątek, zarządza dniem w symulacji, synchronizuje działania wątków inwestorów i spółek.
 * Odpowiada również za pojawianie się nowych inwestorów wraz ze wzrastającą ilością aktyw.
 */
package projekt.model;

import java.util.Random;
import projekt.Ekonomia;
import projekt.view.TabController;

/**
 *
 * @author Vieja
 */
public class Symulator extends TabController implements Runnable{
    Ekonomia ekonomia;
    
    @Override
    public void run() {
        System.out.println("Hello from Symulator!");
        while (true) {
            System.out.println("Dzień "+this.getSimulationDay());
            this.mozeDodajInwestorow();
            for (int i=0;i<ekonomia.getlistaInwestor().size();i++) {
                ekonomia.getlistaInwestor().get(i).losujAktywo(ekonomia.getlistaAktywa());
                ekonomia.getlistaInwestor().get(i).losujFundusz(ekonomia.getlistaFundusz());
                ekonomia.getlistaInwestor().get(i).pizzaGuy();
            }
            for (int i=0;i<ekonomia.getlistaAkcja().size();i++) {
                ekonomia.getlistaAkcja().get(i).pizzaGuy();
            }
            try        
            {
                Thread.sleep(3000);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
            for (int i=0;i<ekonomia.getlistaAktywa().size();i++) {
                ekonomia.getlistaAktywa().get(i).koniecDnia();
            }
            this.newSimulationDay();
        }
    }
    
    public Symulator(Ekonomia ekonomia) {
        this.ekonomia = ekonomia;
    }
    
    public void mozeDodajInwestorow() {
        Random generator = new Random();
        int los=generator.nextInt(5);
        while (ekonomia.getlistaInwestor().size() < 0.75*ekonomia.getlistaAktywa().size()) {
           if (los == 0) ekonomia.dodajInwestor(1);
           else ekonomia.dodajInwestor(0);
           los=generator.nextInt(6);
        }
    }

    @Override
    public void setTable() {
    }
}

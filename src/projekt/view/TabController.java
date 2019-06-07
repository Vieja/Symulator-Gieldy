/*
 * Klasa abstakcyjna dla pozostałych kontolerów oraz Symulacji - 
 * wszystkie te byty muszą mieć dostęp do tego samego obiektu ekonomia
 */
package projekt.view;

import projekt.Ekonomia;

/**
 *
 * @author Vieja
 */
public abstract class TabController {
    private Ekonomia ekonomia;
    private Integer simulationDay = 1;

    public Ekonomia getEkonomia() {
        return ekonomia;
    }  
    public void setEkonomia(Ekonomia e) {
        this.ekonomia=e;
    
}
    public abstract void setTable();
    
    public Integer getSimulationDay() {
        return simulationDay;
    }
    public void newSimulationDay() {
        this.simulationDay = simulationDay+1;
    }
}

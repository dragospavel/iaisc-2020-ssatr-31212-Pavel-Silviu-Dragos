package mas.ssatr.pavel.dragos;

import mas.ssatr.pavel.dragos.service.PetriNetReader;
import mas.ssatr.pavel.dragos.simulation.PetriNetSimulator;

public class Main {

    public static void main(String[] args) {
        PetriNetReader petriNetReader= new PetriNetReader();
        PetriNetSimulator petriNetSimulator = new PetriNetSimulator(petriNetReader);
        petriNetSimulator.simulate();
    }
}

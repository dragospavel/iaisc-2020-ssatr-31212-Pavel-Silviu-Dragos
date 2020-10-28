package mas.ssatr.pavel.dragos.models;

import java.util.List;

public class PetriNet {

    private List<Location> locations;
    private List<Transition> transitions;

    public PetriNet() {
        super();
    }

    public PetriNet(List<Location> locations, List<Transition> transitions) {
        this.locations = locations;
        this.transitions = transitions;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}

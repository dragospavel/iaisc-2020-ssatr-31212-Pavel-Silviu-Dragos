package mas.ssatr.pavel.dragos.simulation;

import mas.ssatr.pavel.dragos.models.Location;
import mas.ssatr.pavel.dragos.models.PetriNet;
import mas.ssatr.pavel.dragos.models.Transition;
import mas.ssatr.pavel.dragos.service.PetriNetReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNetSimulator {

    private PetriNet petriNet;

    private PetriNetReader petriNetReader;

    private boolean active = true;

    private int elapsedTime = 0;

    private Map<String, Integer> executionTimes;
    private Map<String, Integer> executionStartedAt;

    public PetriNetSimulator(PetriNetReader petriNetReader) {
        this.petriNetReader = petriNetReader;
    }

    public void init() {
        this.petriNet = petriNetReader.loadPetriNet("D:\\Facultate\\Master\\An I\\Semestrul 1\\SSATR\\Lab\\tema1\\petrinet.json");
    }

    public void simulate() {
        this.init();
        List<Transition> inExecutionTransitions = new ArrayList<Transition>();
        executionTimes = new HashMap<>();
        executionStartedAt = new HashMap<>();

        while (active) {


            evaluate(inExecutionTransitions);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedTime++;
        }
    }

    public void evaluate(List<Transition> inExecutionTransitions) {
        List<Transition> transitions = petriNet.getTransitions();

        boolean isPetriNetExecutable = false;

        for (Transition transition : transitions) {

            if (isTransitionExecutable(transition)) {

                int transitionTime = 0;

                if (transition.getMaxTime() != 0) {
                    transitionTime = transition.getMinTime() + (int) (Math.random() * ((transition.getMaxTime() - transition.getMinTime()) + 1));
                }

                if (transition.getMinTime() == transition.getMaxTime()) {
                    transitionTime = transition.getMaxTime();
                }

                executionTimes.put(transition.getName(), transitionTime);
                executionStartedAt.put(transition.getName(), elapsedTime);

                isPetriNetExecutable = true;
                inExecutionTransitions.add(transition);
                updatePetriNet(transition, "prev");
                if (executionStartedAt.get(transition.getName()) + executionTimes.get(transition.getName()) == elapsedTime) {
                    updatePetriNet(transition, "next");
                    inExecutionTransitions.remove(transition);
                }
            }

            if (inExecutionTransitions.size() > 0 && inExecutionTransitions.contains(transition)) {
                isPetriNetExecutable = true;
                if (executionStartedAt.get(transition.getName()) + executionTimes.get(transition.getName()) == elapsedTime) {
                    updatePetriNet(transition, "next");
                    inExecutionTransitions.remove(transition);
                }
            }


        }

        if (!isPetriNetExecutable) {
            active = false;
        }
    }

    public boolean isTransitionExecutable(Transition t) {
        int foundLocations = 0;

        // getting all the names of the prev locations
        List<String> locationNames = t.getPrevLocation();

        // iterating over all names of prev location of transition t
        for (String locationName : locationNames) {

            // iterating over all location that contains tokens
            for (Location location : petriNet.getLocations()) {
                if (location.getName().equals(locationName) && location.getTokenNo() > 0) {
                    foundLocations++;
                }
            }
        }

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("Transition checked: " + t.getName() + " and is executable? " + (foundLocations == t.getPrevLocation().size()) + " found lcoations: " + foundLocations + " at time: " + elapsedTime);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        return foundLocations == t.getPrevLocation().size();
    }

    public void updatePetriNet(Transition transition, String whichLocationToUpdate) {
        List<Location> locations = petriNet.getLocations();
        for (Location location : locations) {


            if (whichLocationToUpdate.equals("next")) {
                List<String> nextLocations = transition.getNextLocation();
                for (String nextLocation : nextLocations) {
                    if (location.getName().equals(nextLocation)) {
                        location.setTokenNo(location.getTokenNo() + 1);
                    }
                }
            }
            if (whichLocationToUpdate.equals("prev")) {
                List<String> prevLocations = transition.getPrevLocation();
                for (String prevLocation : prevLocations) {
                    if (location.getName().equals(prevLocation)) {
                        location.setTokenNo(location.getTokenNo() - 1);
                    }
                }
            }
        }
        for (Location location : petriNet.getLocations()) {
            writeInFile("Location name: " + location.getName() + " No of tokens: " + location.getTokenNo() + " At time: " + elapsedTime);
        }
        writeInFile("###################################################################################");
    }

    public void writeInFile(String text) {
        try {
            FileWriter fw = new FileWriter("Output.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

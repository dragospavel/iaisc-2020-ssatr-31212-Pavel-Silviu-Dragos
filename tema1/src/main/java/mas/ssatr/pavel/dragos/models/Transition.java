package mas.ssatr.pavel.dragos.models;

import java.util.List;

public class Transition {

    private String name;
    private List<String> nextLocation;
    private List<String> prevLocation;
    private int minTime;
    private int maxTime;

    public Transition() {
        super();
    }

    public Transition(String name, List<String> nextLocation, List<String> prevLocation, int minTime, int maxTime) {
        this.name = name;
        this.nextLocation = nextLocation;
        this.prevLocation = prevLocation;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(List<String> nextLocation) {
        this.nextLocation = nextLocation;
    }

    public List<String> getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(List<String> prevLocation) {
        this.prevLocation = prevLocation;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}

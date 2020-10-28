package mas.ssatr.pavel.dragos.models;

import java.util.List;

public class Location {

    private String name;
    private List<String> nextTransition;
    private int tokenNo;

    public Location() {
        super();
    }

    public Location(String name, List<String> nextTransition, int tokenNo) {
        this.name = name;
        this.nextTransition = nextTransition;
        this.tokenNo = tokenNo;
    }

    public List<String> getNextTransition() {
        return nextTransition;
    }

    public void setNextTransition(List<String> nextTransition) {
        this.nextTransition = nextTransition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(int tokenNo) {
        this.tokenNo = tokenNo;
    }
}

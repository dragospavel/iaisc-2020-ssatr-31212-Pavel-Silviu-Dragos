package mas.ssatr.pavel.dragos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mas.ssatr.pavel.dragos.models.PetriNet;

import java.io.File;
import java.io.IOException;

public class PetriNetReader {

    public PetriNet loadPetriNet(String jsonFileName) {
        ObjectMapper mapper = new ObjectMapper();
        PetriNet petriNet = new PetriNet();
        try {
            petriNet = mapper.readValue(new File(jsonFileName), PetriNet.class);
            System.out.println(petriNet.getLocations().get(1).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return petriNet;
    }
}

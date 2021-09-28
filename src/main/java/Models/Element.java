package Models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Set;

public class Element {

   private String id;
   private String[] nodes;


    public Element(String id, String[] nodes) {
        this.id = id;
        this.nodes = nodes;
    }

    public Element() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getNodes() {
        return nodes;
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }
}

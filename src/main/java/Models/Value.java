package Models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Value {

   private String element_id;
    private String value;


    public Value(String element_id, String value) {
        this.element_id = element_id;
        this.value = value;
    }

    public Value() {
    }

    public String getElement_id() {
        return element_id;
    }

    public void setElement_id(String element_id) {
        this.element_id = element_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}

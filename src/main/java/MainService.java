import Models.Element;
import Models.Node;
import Models.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class MainService {

    List<Element> elementList = new ArrayList<Element>();
    List<Node> nodeList = new ArrayList<Node>();
    List<Value> valueList = new ArrayList<Value>();

    List<Value> neighboursForNode = new ArrayList<Value>();

    List<Value> findedViewSpots = new ArrayList<Value>();

    ObjectMapper objectMapper = new ObjectMapper();

    void loadData(String jsonSrc) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(jsonSrc));
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray elementsListJSON = (JSONArray) jsonObject.get("elements");
        JSONArray nodesListJSON = (JSONArray) jsonObject.get("nodes");
        JSONArray valuesListJSON = (JSONArray) jsonObject.get("values");

        for (Object e : elementsListJSON
        ) {
            Element element = objectMapper.readValue(e.toString(), Element.class);
            elementList.add(element);
        }
        for (Object nodeI : nodesListJSON
        ) {
            Node node = objectMapper.readValue(nodeI.toString(), Node.class);
            nodeList.add(node);
        }
        for (Object valueI : valuesListJSON
        ) {
            Value value = objectMapper.readValue(valueI.toString(), Value.class);
            valueList.add(value);
        }
    }


    void resultGetter(int n) {

        System.out.println("[");
        int soutCounter = 0;

        for (Value iteratorForValueList : valueList) {
            boolean skip = false;
            double elementValue = Double.parseDouble(iteratorForValueList.getValue());
//            if (soutCounter == n) break;


            Element element = elementList.stream()
                    .filter(e -> iteratorForValueList.getElement_id().equals(e.getId()))
                    .findAny()
                    .orElse(null);

            for (int i = 0; i < 3; i++) { // iterate on element nodes
//                if (soutCounter == n) break;
                if (skip) continue;
                for (Element elementToCompare : elementList) {

                    for (int j = 0; j < 3; j++) {// iterate on elementToCompare nodes

                        if (Integer.parseInt(element.getNodes()[i]) == Integer.parseInt(elementToCompare.getNodes()[j])
                                && Integer.parseInt(element.getId()) != Integer.parseInt(elementToCompare.getId())) {

                            Value elementToCompareValue = valueList.stream()
                                    .filter(e -> elementToCompare.getId().equals(e.getElement_id()))
                                    .findAny()
                                    .orElse(null);

                            double elementToCompareValueD = Double.parseDouble(elementToCompareValue.getValue());

                            if (elementValue >= elementToCompareValueD) {
                                this.neighboursForNode.add(elementToCompareValue);
                            } else if (elementValue < elementToCompareValueD) {
                                skip = true;
                            }
//
                        }
                    }
                }
            }


            if (this.neighboursForNode.size() > 0) {
                double iteratorForValueListMax = 0;
                for (Value v : this.neighboursForNode) {
                    double vValue = Double.parseDouble(v.getValue());

                    if (vValue >= iteratorForValueListMax) {
                        iteratorForValueListMax = vValue;
                    }
                }
                if (elementValue >= iteratorForValueListMax) {
                    findedViewSpots.add(iteratorForValueList);
                    this.neighboursForNode = new ArrayList<Value>();
                    soutCounter++;
                }
            }
        }

        this.displayViewPoints(n);
        System.out.println("]");

    }

    void displayViewPoints(int n) {
        this.findedViewSpots.sort(Comparator.comparing(Value::getValue).reversed());
        int counter = 0;

        for (int i = 0; i < n; i++) {
            if (i == findedViewSpots.size()){
                break;
            }
            Value v = findedViewSpots.get(i);
            boolean skip = false;
            if (i > 0) {
                Value pV = findedViewSpots.get(i - 1);
                if (Double.parseDouble(v.getValue()) == Double.parseDouble(pV.getValue())) {
                    skip = true;
                }
            }
            if (skip) continue;
            System.out.println(
                    "{ element_id: " + v.getElement_id() + ", value: " +
                            v.getValue() + " },");
            counter++;


        }
    }

    void printValueList() {
        for (Value v : valueList) {
            System.out.println(v.getValue());
        }
    }

    void printNodeList() {
        for (Node n : nodeList) {
            System.out.println(n.getId());
        }
    }

}


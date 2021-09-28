package Models;

public class Node {


   private String id;
   private String x;
   private String y;


    public Node(String id, String x, String y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Node() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}

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
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MainService mainService = new MainService();




//        String jsonSoruce = args[0];
//        int n = Integer.parseInt(args[1]);


        int n = 5;
        String jsonSoruce = "D:\\.PERSONAL PROJECTS\\TEST ARRK\\ViewSpotFinder\\src\\main\\JSON\\mesh_x_sin_cos_10000.json";



        mainService.loadData(jsonSoruce);
//        mainService.orderdValues();

        mainService.resultGetter(n);



    }
}









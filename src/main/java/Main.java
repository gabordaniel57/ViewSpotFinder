
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MainService mainService = new MainService();


        String jsonSoruce = args[0];
        int n = Integer.parseInt(args[1]);


        mainService.loadData(jsonSoruce);

        mainService.resultGetter(n);


    }
}









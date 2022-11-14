
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import reflection.ReflectionFrameWork;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        
        ReflectionFrameWork reflectionFrameWork = new ReflectionFrameWork("ConfigMVC.json","votos1");
        reflectionFrameWork.Execute();

    }
}

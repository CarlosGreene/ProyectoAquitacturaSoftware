
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import org.json.simple.parser.ParseException;

import reflection.ReflectionFrameWork;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        
        ReflectionFrameWork reflectionFrameWork = new ReflectionFrameWork("ConfigMVC.json","Votos1");
        Votes votes = new Votes();
        ArrayList<Observer> observers = new ArrayList<Observer>();
        observers.add(new PieChart());
        observers.add(new BarChart());
        observers.add(new table());

        reflectionFrameWork.Execute(votes, observers);

    }
}


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import log.Log;
import org.apache.log4j.Logger;

import org.json.simple.parser.ParseException;

import reflection.ReflectionFrameWork;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {   
        ReflectionFrameWork reflectionFrameWork = new ReflectionFrameWork("ConfigMVC.json","Votos1");
        Votes votes = new Votes();
        ArrayList<Observer> observers = new ArrayList<Observer>();

        PieChart pie = new PieChart();
        BarChart bar = new BarChart();
        Table table = new Table();

        observers.add(pie);
        observers.add(bar);
        observers.add(table);

        reflectionFrameWork.Execute(votes, observers);

    }
}


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import log.Log;
import org.apache.log4j.Logger;

import org.json.simple.parser.ParseException;

import reflection.ReflectionFrameWork;

public class main {

    private static final Logger LOG = Log.getLogger(main.class);

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {        
        ReflectionFrameWork reflectionFrameWork = new ReflectionFrameWork("ConfigMVC.json","Votos1");
        Votes votes = new Votes();
        ArrayList<Observer> observers = new ArrayList<Observer>();
        //observers.add(new PieChart());
        //observers.add(new BarChart());
        //observers.add(new table());

        LOG.info(observers.add(new PieChart()));
        LOG.info(observers.add(new BarChart()));
        LOG.info(observers.add(new table()));

        reflectionFrameWork.Execute(votes, observers);

    }
}

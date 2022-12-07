package reflection;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javax.swing.JFrame;

import log.Log;
import mvc.view.View;
import parseJSON.JSON_Parser;
import sql.DBCandidato;

public class ReflectionFrameWork {
    private final HashMap<String, Class> main_components;
    private final HashMap<String, Class> toNotified_Class;
    private final HashMap<String, String> uml_components;
    private final JSON_Parser parser;
    private final ClassFinder finder;
    private final Log log;
    private DBCandidato dbCandidato;

    public ReflectionFrameWork(String config_file_name, String transaction_file_name) {
        finder = new ClassFinder();
        parser = new JSON_Parser(config_file_name, transaction_file_name);
        log = Log.getInstance();
        main_components = new HashMap<>();
        toNotified_Class = new HashMap<>();
        uml_components = new HashMap<>();

        StartConfiguration();
    }

    private void StartConfiguration() {
        SearchClasses(parser.getParseServices().getMainComponents().entrySet(), main_components);
        SearchClasses(parser.getParseServices().getToNotifiedComponents().entrySet(), toNotified_Class);
        searchUMLComponents(parser.getParseServices().getUMLComponents().entrySet());
    }

    private void SearchClasses(Set<Map.Entry<String, String>> componentsList, HashMap<String, Class> toSave) {
        for (Map.Entry<String, String> component : componentsList) {
            toSave.put(component.getKey(), finder.FindClass(component.getValue()));
            System.out.println(component.getKey() + ":" + component.getValue());
        }
    }

    private void searchUMLComponents(Set<Map.Entry<String, String>> componentsList){
        for (Map.Entry<String, String> component : componentsList) {
            uml_components.put(component.getKey(), component.getValue());
            System.out.println(component.getKey() + ":" + component.getValue());
        }
    }

    private void EmssambleMainComponents(View principalView, ArrayList<Observer> observers) {
        Class<?> classModel = main_components.get("Modelo");
        Class<?> classControl = main_components.get("Control");

        ArrayList<Object> objects = new ArrayList<>();

        try {            
            Object controlObj = classControl.getConstructor().newInstance();
            
            Class[] ArgTypeModelConstructor = {Integer.TYPE, String.class};
            Object modeloObj1 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(new Integer(1), new String("Candidato uno"));
            Object modeloObj2 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(new Integer(2), new String("Candidato dos"));
            Object modeloObj3 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(new Integer(3), new String("Candidato tres"));

            Method addObservers = classModel.getDeclaredMethod("addObserver", Class.forName("java.util.Observer"));
            for (Object frame : observers) {
                addObservers.invoke(modeloObj1, frame);
                addObservers.invoke(modeloObj2, frame);
                addObservers.invoke(modeloObj3, frame);
                objects.add(frame);
            }
            
            Method addModels = classControl.getDeclaredMethod("addModels", String.class, Class.forName("mvc.model.Model"));
            addModels.invoke(controlObj, new String("Candidato uno"), modeloObj1);
            addModels.invoke(controlObj, new String("Candidato dos"), modeloObj2);
            addModels.invoke(controlObj, new String("Candidato tres"), modeloObj3);

            Method setView = classControl.getDeclaredMethod("setView", JFrame.class);
            setView.invoke(controlObj, principalView);

            Class<?> classView = Class.forName("mvc.view.View");
            Method setControl =  classView.getMethod("setControl", Class.forName("mvc.controler.Control"));
            setControl.invoke(principalView, controlObj);

            objects.add(modeloObj1);
            objects.add(modeloObj2);
            objects.add(modeloObj3);
            objects.add(controlObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void EmssambleNotifiedClass(){
        Class<?> classModel = toNotified_Class.get("Object");
        log.getLogger(classModel);
    }

    private void EmssambleUMLComponents(){
        String url = uml_components.get("Url");
        String user = uml_components.get("Usuario");
        String password = uml_components.get("Contraseña");

        dbCandidato = new DBCandidato(url, user, password);
    }

    public void Execute(View principalView, ArrayList<Observer> observers) {
        EmssambleUMLComponents();
        EmssambleNotifiedClass();
        EmssambleMainComponents(principalView, observers);
    }

}

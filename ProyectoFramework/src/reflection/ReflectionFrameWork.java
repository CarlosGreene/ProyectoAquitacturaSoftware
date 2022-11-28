package reflection;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javax.swing.JFrame;

import mvc.controler.Control;
import mvc.view.View;
import parseJSON.JSON_Parser;

public class ReflectionFrameWork {
    private final HashMap<String, Class> main_components;
    private final JSON_Parser parser;
    private final ClassFinder finder;

    public ReflectionFrameWork(String config_file_name, String transaction_file_name) {
        finder = new ClassFinder();
        parser = new JSON_Parser(config_file_name, transaction_file_name);
        main_components = new HashMap<>();

        StartConfiguration();
    }

    private void StartConfiguration() {
        SearchClasses(parser.getParseServices().getMainComponents().entrySet(), main_components);
    }

    private void SearchClasses(Set<Map.Entry<String, String>> componentsList, HashMap<String, Class> toSave) {
        for (Map.Entry<String, String> component : componentsList) {
            toSave.put(component.getKey(), finder.FindClass(component.getValue()));
            System.out.println(component.getKey() + ":" + component.getValue());
        }
    }

    private void EmssambleMainComponents(View principalView, ArrayList<Observer> observers) {
        Class<?> classModel = main_components.get("Modelo");
        Class<?> classControl = main_components.get("Control");

        try {            
            Object controlObj = classControl.getConstructor().newInstance();
            
            Class[] ArgTypeModelConstructor = {Integer.TYPE, String.class};
            Object[] argListModel = new Object[2];
            argListModel[0] = new Integer(1);
            argListModel[1] = new String("Candidato uno");
            Object modeloObj1 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(argListModel);
            argListModel[0] = new Integer(2);
            argListModel[1] = new String("Candidato dos");
            Object modeloObj2 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(argListModel);
            argListModel[0] = new Integer(3);
            argListModel[1] = new String("Candidato tres");
            Object modeloObj3 = classModel.getConstructor(ArgTypeModelConstructor).newInstance(argListModel);

            Method addObservers = classModel.getDeclaredMethod("addObserver", Class.forName("java.util.Observer"));
            for (Object frame : observers) {
                addObservers.invoke(modeloObj1, frame);
                addObservers.invoke(modeloObj2, frame);
                addObservers.invoke(modeloObj3, frame);
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

        } catch (Exception e) { //Cambiar por una excepcion personalizada
            e.printStackTrace();
        }

    }

    public void Execute(View principalView, ArrayList<Observer> observers) {
        EmssambleMainComponents(principalView, observers);
    }

}

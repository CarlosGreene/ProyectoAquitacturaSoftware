package reflection;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import parseJSON.JSON_Parser;

public class ReflectionFrameWork {
    private final HashMap<String, Class> main_components;
    private final HashMap<String, Class> toNotified_views;
    private final JSON_Parser parser;
    private final ClassFinder finder;

    public ReflectionFrameWork(String config_file_name, String transaction_file_name) {
        finder = new ClassFinder();
        parser = new JSON_Parser(config_file_name, transaction_file_name);
        toNotified_views = new HashMap<>();
        main_components = new HashMap<>();

        StartConfiguration();
    }

    private void StartConfiguration() {
        SearchClasses(parser.getParseServices().getMainComponents().entrySet(), main_components);
        SearchClasses(parser.getParseServices().getToNotifiedComponents().entrySet(), toNotified_views);
    }

    private void SearchClasses(Set<Map.Entry<String, String>> componentsList, HashMap<String, Class> toSave) {
        for (Map.Entry<String, String> component : componentsList) {
            toSave.put(component.getKey(), finder.FindClass(component.getValue()));
            System.out.println(component.getKey() + ":" + component.getValue());
        }
    }

    private void EmssambleMainComponents() {
        Class<?> classView = main_components.get("Vista");
        Class<?> classModel = main_components.get("Modelo");
        Class<?> classControl = main_components.get("Control");

        try {
            Class[] ArgTypeControlConstructor = {Class.forName("MVC.Model"), Class.forName("MVC.View")};

            //Created the Controler instance
            Object modeloObj = classModel.getConstructor().newInstance();
            Object vistaObj = classView.getConstructor().newInstance();
            Object ControlerInstanced = classControl.getConstructor(ArgTypeControlConstructor).newInstance(modeloObj, vistaObj);
            Method setControl = classView.getDeclaredMethod("setControl", Class.forName("MVC.Control"));
            setControl.invoke(vistaObj, ControlerInstanced);
            EmssambleViewComponents(modeloObj);

            if (IsPrincipalView()) {
                Method attatchObserver = classModel.getDeclaredMethod("attatchObserver", Class.forName("MVC.View"));
                Method setSubject = classView.getMethod("setSubject", Class.forName("MVC.Model"));
                attatchObserver.invoke(modeloObj, vistaObj);
                setSubject.invoke(vistaObj, modeloObj);
            }
        } catch (Exception e) { //Cambiar por una excepcion personalizada
            e.printStackTrace();
        }

    }

    private void EmssambleViewComponents(Object model) {
        try {
            Method attatchObserver = main_components.get("Modelo").getDeclaredMethod("attatchObserver", Class.forName("MVC.View"));
            for (Map.Entry<String, Class> component : toNotified_views.entrySet()) {
                Object toAlertView = component.getValue().getConstructor().newInstance();
                Method setSubject = component.getValue().getMethod("setSubject", Class.forName("MVC.Model"));
                attatchObserver.invoke(model, toAlertView);
                setSubject.invoke(toAlertView, model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Execute() {
        EmssambleMainComponents();
    }

    private boolean IsPrincipalView() {
        for (Map.Entry component : toNotified_views.entrySet()) {
            if (main_components.get("Vista").toString().equals(component.getValue().toString())) {
                System.out.println("Si hay");
                return true;
            }
        }
        System.out.println("No hay");
        return false;
    }

}

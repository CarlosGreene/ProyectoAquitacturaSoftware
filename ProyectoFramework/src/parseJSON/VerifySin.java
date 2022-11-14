package parseJSON;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VerifySin{
    public JSONObject json_Object;
    public VerifySin(String path, String transaccion) throws FileNotFoundException, IOException, ParseException{
        try {    
            this.json_Object = jParse(path,transaccion);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public JSONObject jParse(String path, String transaccion) throws FileNotFoundException, IOException, ParseException{
        Object obj = new JSONParser().parse(new FileReader(path));
        String jsonTransaccion;
        boolean existANotficar = false;
        JSONArray je = (JSONArray) obj;
        Iterator ltrTransaction = je.iterator();

        while (ltrTransaction.hasNext()){

            JSONObject jo = (JSONObject) ltrTransaction.next();
            jsonTransaccion  = (String) jo.get("transaccion");  
            if(jsonTransaccion.equals(transaccion)){
                String componentsKeys[] = new String[3];
                String componentsValue[]= new String[3];
        
                Map componentes = ((Map)jo.get("componentes"));   
                if(componentes==null){
                    System.out.println("No esta definido componentes");
                    return null;
                }       
                Iterator<Map.Entry> itrComponents = componentes.entrySet().iterator();
                int i=0;
                while (itrComponents.hasNext()) { 
                    Map.Entry pair = itrComponents.next();
                    componentsKeys[i]= (String) pair.getKey();
                    componentsValue[i]= (String) pair.getValue();
                    i++;
                }
                Map anotificar = ((Map)jo.get("ANotificar"));

                if(anotificar==null){
                    System.out.println("No esta definido ANotificar");
                    return null;
                }
                
                Iterator<Map.Entry> itrNotificar = anotificar.entrySet().iterator();
                while (itrNotificar.hasNext()) {
                    Map.Entry pair = itrNotificar.next();
                    existANotficar = true;
                    if( ((String) pair.getKey()).equals("")) {
                        System.out.println("Vista a notificar no definida");
                        return null;
                    }
                    if(((String) pair.getValue()).equals("")) {
                        System.out.println("El valor de "+ pair.getKey()+ " a notificar no definida");
                        return null;
                    }
                }
                if(syntax(componentsKeys,componentsValue)==false){
                    return null;
                }
                if(existANotficar==false){
                    System.out.println("No se encontró una vista a notificar");
                    return null;
                }
                return jo;
            }  
        } 
        System.out.println("No se encontró una transaccion con ese nombre");
       return null;
    }  
    public boolean syntax(String components[], String compoName[]){
        String vista="";
        String controlador="";
        String modelo="";

        for(int i=0; i<components.length; i++){
            if(components[i].equals("Vista")){
                if(compoName[i].equals("")){
                    System.out.println("No está definido un valor para vista en los componentes");
                }
                vista = components[i];
            }
            if(components[i].equals("Control")){
                if(compoName[i].equals("")){
                    System.out.println("No está definido un valor para control  en los componentes");
                }
                controlador = components[i];
            }
            if(components[i].equals("Modelo")){
                if(compoName[i].equals("")){
                    System.out.println("No está definido un valor para modelo en los componentes");
                }
                modelo = components[i];
            }
        }
        if(vista.equals("")){
            System.out.println("No está definido una Vista en los componentes");
            return false;
        }else if(controlador.equals("")){
            System.out.println("No está definido un control en los componentes");
            return false;
        }else if(modelo.equals("")){
            System.out.println("No está definido un modelo en los componentes");
            return false;
        }else{
            return true;
        }

    }

    public JSONObject getJsonObject(){
        return this.json_Object;
    }
}

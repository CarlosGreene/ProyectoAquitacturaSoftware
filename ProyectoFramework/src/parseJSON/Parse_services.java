package parseJSON;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Parse_services {
    private final JSON_Parser Json_parser;

    public Parse_services(JSON_Parser json_parser) {
        Json_parser = json_parser;
    }

    public Map<String,String> getMainComponents() {
        Map<String, String> MainComponents = ((HashMap<String,String>) Json_parser.getMVConfig_file().get("componentes"));
        return MainComponents;
    }
}

package parseJSON;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JSON_Parser {
    private JSONObject MVConfig_file;
    private final Parse_services parse_services;

    public JSON_Parser(String file_name, String transaction) {
        try {
            VerifySin transactionSyntax = new VerifySin(file_name, transaction);
            Object json_obj = transactionSyntax.getJsonObject();
            MVConfig_file = (JSONObject) json_obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        parse_services = new Parse_services(this);
    }

    public Parse_services getParseServices() {
        return parse_services;
    }

    public JSONObject getMVConfig_file() {
        return MVConfig_file;
    }
}

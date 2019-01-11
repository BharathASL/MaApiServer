package in.ma.apis.tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bharath
 */
public class Commons {

    public static JSONObject generateResponse(boolean success, String... failedMessageInElement1) {
        JSONObject response = new JSONObject();
        try {
            response.put("success", success);
            if (failedMessageInElement1.length > 0) {
                response.put("error_message", failedMessageInElement1[0]);
            }
            return response;
        } catch (JSONException e) {
            return null;
        }
    }
}

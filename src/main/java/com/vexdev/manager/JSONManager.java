package com.vexdev.manager;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 30/10/13
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class JSONManager {

    /**
     * Returns a JSON encoded Error.
     * @param exception Exception string to encode
     * @return JSON Message
     */
    public static String fromError(String exception) {
        return "{\"error\":\""+exception+"\"}";
    }

    /**
     * Builds a JSON from a map of id to names
     * @param idName Map of ID to names
     * @return JSON String
     */
    public static String fromNameIDMap(Map<String, String> idName) {
        String jsonList = "{}";
        if(idName != null && idName.size() > 0) {
            jsonList = "[";
            Iterator<String> it = idName.keySet().iterator();
            while (it.hasNext()) {
                String id = it.next();
                jsonList += "{\"name\":\"";
                jsonList += idName.get(id);
                jsonList += "\",\"id\":\"" + id +"\"}";
                if(it.hasNext())
                    jsonList += ",";
            }
            jsonList += "]";
        }
        return jsonList;
    }

    /**
     * Creates a JSON string that reports result code OK
     * @return JSON String.
     */
    public static String fromSimpleResultOK() {
        return "{\"result\":\"ok\"}";
    }
}

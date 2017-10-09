/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author Etienne
 */
public class SoajsRequestUtilities {

    public static String getHost(JSONObject soajs) {
        String host = soajs.getJSONObject("awareness").getString("host");
        int port = soajs.getJSONObject("awareness").getInt("port");

        return host + ":" + port + "/";
    }

    public static String getHost(JSONObject soajs, String serviceName) {
        String output = getHost(soajs);

        if (!serviceName.equals("controller")) {
            return output + serviceName + "/";
        } else {
            return output;
        }
    }

    public static String getHost(JSONObject soajs, String serviceName, String version) {
        String output = getHost(soajs);

        if (!serviceName.equals("controller")) {
            return output + serviceName + "/v" + version + "/";
        } else {
            return output;
        }
    }

    public static JSONObject merge(JSONObject obj1, JSONObject obj2) {
        if (obj1 == null) {
            return obj2;
        }

        if (obj2 == null) {
            return obj1;
        }

        JSONObject merged = new JSONObject();
        JSONObject[] objs = new JSONObject[]{obj1, obj2};
        for (JSONObject obj : objs) {
            Iterator it = obj.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                merged.put(key, obj.get(key));
            }
        }
        
        return merged;
    }
}

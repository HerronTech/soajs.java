/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Etienne
 */
public class SoajsRegistry {

    public static JSONObject registryStruct = new JSONObject();
    public static JSONObject autoReloadTimeout = new JSONObject();
    private static Timer timer = new Timer();
    public static String env;
    public static String serviceName;
    public static String soajsRegistryApi;
    public static String setBy;

    public static boolean reload() {
        if (env != null && serviceName != null) {
            return execRegistry();
        } else {
            new Error("Cannot reload registry. Env and serviceName are not set").printStackTrace();
            return false;
        }
    }

    public static JSONObject getDatabases() {
        if (env != null && registryStruct.has(env)) {
            if (!registryStruct.getJSONObject(env).has("tenantMetaDB") && !registryStruct.getJSONObject(env).has("coreDB")) {
                return null;
            } else {
                return SoajsRequestUtilities.merge(registryStruct.getJSONObject(env).getJSONObject("tenantMetaDB"), registryStruct.getJSONObject(env).getJSONObject("coreDB"));
            }
        }
        return null;
    }

    public static JSONObject getDatabases(String dbName) {
        if (env != null && registryStruct.has(env)) {
            if (registryStruct.getJSONObject(env).has("tenantMetaDB") 
                    && dbName!= null && !dbName.isEmpty() && registryStruct.getJSONObject(env).getJSONObject("tenantMetaDB").has(dbName)) {
                return registryStruct.getJSONObject(env).getJSONObject("tenantMetaDB").getJSONObject(dbName);
            }
            if (registryStruct.getJSONObject(env).has("coreDB")  
                    && dbName!= null && !dbName.isEmpty() && registryStruct.getJSONObject(env).getJSONObject("coreDB").has(dbName)) {
                return registryStruct.getJSONObject(env).getJSONObject("coreDB").getJSONObject(dbName);
            }
        }
        return null;
    }

    public static JSONObject getServiceConfig() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("serviceConfig");
        }
        return null;
    }

    public static JSONObject getDeployer() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("deployer");
        }
        return null;
    }

    public static JSONObject getCustom() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("custom");
        }
        return null;
    }

    public static JSONObject getResources() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("resources");
        }
        return null;
    }

    public static JSONObject getResources(String resourceName) {
        if (env != null && registryStruct.has(env) && registryStruct.getJSONObject(env).has("resources") 
                && resourceName!= null && !resourceName.isEmpty() && registryStruct.getJSONObject(env).getJSONObject("resources").has(resourceName)) {
            return registryStruct.getJSONObject(env).getJSONObject("resources").getJSONObject(resourceName);
        }
        return null;
    }

    public static JSONObject getServices() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("services");
        }
        return null;
    }

    public static JSONObject getServices(String serviceName) {
        if (env != null && registryStruct.has(env) && registryStruct.getJSONObject(env).has("services") 
                && serviceName!= null && !serviceName.isEmpty() && registryStruct.getJSONObject(env).getJSONObject("services").has(serviceName)) {
            return registryStruct.getJSONObject(env).getJSONObject("services").getJSONObject(serviceName);
        }
        return null;
    }

    public static JSONObject getDaemons() {
        if (env != null && registryStruct.has(env)) {
            return registryStruct.getJSONObject(env).getJSONObject("daemons");
        }
        return null;
    }

    public static JSONObject getDaemons(String daemonName) {
        if (env != null && registryStruct.has(env) && registryStruct.getJSONObject(env).has("daemons") 
                & daemonName!= null && !daemonName.isEmpty() && registryStruct.getJSONObject(env).getJSONObject("daemons").has(daemonName)) {
            return registryStruct.getJSONObject(env).getJSONObject("daemons").getJSONObject(daemonName);
        }
        return null;
    }

    public static boolean execRegistry() {

        if (soajsRegistryApi == null || !soajsRegistryApi.contains(":")) {
            new Error("Invalid format for SOAJS_REGISTRY_API [hostname:port]: " + soajsRegistryApi).printStackTrace();
            return false;
        }

        String port = soajsRegistryApi.substring(soajsRegistryApi.indexOf(":") + 1);

        try {
            Integer.parseInt(port);
        } catch (Exception e) {
            new Error("port must be integer: [" + port + "]").printStackTrace();
            return false;
        }

        String urlPath = "http://" + soajsRegistryApi + "/getRegistry?env=" + env + "&serviceName=" + serviceName;

        JSONObject data = request(urlPath); // get registry data

        if (data == null || !data.has("environment")) {
            return false;
        }

        SoajsRegistry.registryStruct.put(data.getString("environment"), data);

        // autoreload registry stuff
        JSONObject serviceConfig = getServiceConfig();

        if (serviceConfig != null && serviceConfig.has("awareness") && serviceConfig.getJSONObject("awareness").has("autoRelaodRegistry")) {
            if (!autoReloadTimeout.has(env)) {
                autoReloadTimeout.put(env, new JSONObject());
            }

            if (autoReloadTimeout.getJSONObject(env).has("timeout")) {
                timer.cancel();
                timer.purge();
            } else {
                autoReloadTimeout.getJSONObject(env).put("setBy", setBy);
                autoReloadTimeout.getJSONObject(env).put("timeout", "TIMEOUT");

                timer.schedule(new SoajsTimerTask(), 0, serviceConfig.getJSONObject("awareness").getLong("autoRelaodRegistry"));
            }
        }

        return true;
    }

    private static JSONObject request(String urlPath) {
        try {
            
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();

            int status = urlConnection.getResponseCode();
            InputStream inStream = null;

            if (status == 200) {
                inStream = urlConnection.getInputStream();
            } else {
                inStream = urlConnection.getErrorStream();
            }

            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }

            JSONObject output = (JSONObject) new JSONTokener(response).nextValue();

            if (output.has("result") && output.getBoolean("result")) {
                if (output.has("data")) {
                    return output.getJSONObject("data");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
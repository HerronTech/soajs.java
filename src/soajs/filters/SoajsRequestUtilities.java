/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import org.json.JSONObject;

/**
 *
 * @author Etienne
 */
public class SoajsRequestUtilities {
    public static String getHost(JSONObject soajs){
        String host = soajs.getJSONObject("awareness").getString("host");
        int port = soajs.getJSONObject("awareness").getInt("port");
        
        return host+":"+port+"/";
    }
    
    public static String getHost(JSONObject soajs, String serviceName){
        String host = soajs.getJSONObject("awareness").getString("host");
        int port = soajs.getJSONObject("awareness").getInt("port");
        
        if(!serviceName.equals("controller")){
            return host+":"+port+"/"+serviceName+"/";
        }else{
            return host+":"+port+"/";
        }
    }
}

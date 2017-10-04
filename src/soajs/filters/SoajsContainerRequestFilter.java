package soajs.filters;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.json.JSONObject;

/**
 *
 * @author Etienne
 */
public class SoajsContainerRequestFilter implements ContainerRequestFilter {
    
    @Override
    public ContainerRequest filter(ContainerRequest request) {
        System.out.println("Initiating Soajs Container Request Filter ...");

        // construct soajs JSONObject
        JSONObject soajs = contrcutSoajs(request);
        System.out.println("Soajs object constructed:");
        System.out.println("-------------------------");
        System.out.println(soajs);
        System.out.println("-------------------------");

        String soajsRegistryApi = System.getenv("SOAJS_REGISTRY_API");
        String soajsEnv = System.getenv("SOAJS_ENV");

        if (soajsRegistryApi != null && soajsEnv != null) {

            SoajsRegistry.env = soajsEnv;
            SoajsRegistry.serviceName = "test";
            SoajsRegistry.soajsRegistryApi = soajsRegistryApi;
            SoajsRegistry.setBy = "daher";
            
            SoajsRegistry.execRegistry();
        }

        soajs.put("soajsRegistryApi", soajsRegistryApi);
        soajs.put("soajsEnv", soajsEnv);

        // create headers
        InBoundHeaders headers = new InBoundHeaders();

        // append soajs to headers (as a string)
        headers.add("soajs", soajs.toString());
        System.out.println("Soajs appended to headers");

        // Assign header to request
        request.setHeaders((InBoundHeaders) headers);
        System.out.println("Request updated successfully!");

        return request;
    }
    
    /**
     * map injected object & construct SOAJS object
     *
     * @param request
     * @return JSONObject
     */
    private JSONObject contrcutSoajs(ContainerRequest request) {
        JSONObject injectedObject = mapInjectedObject(request);
        JSONObject soajs = new JSONObject();

        if (injectedObject.has("application")
                && injectedObject.getJSONObject("application").has("package")
                && injectedObject.has("key")
                && injectedObject.has("tenant")) {

            soajs = new JSONObject();

            JSONObject tenant = injectedObject.getJSONObject("tenant");

            JSONObject key = new JSONObject();
            key.put("iKey", injectedObject.getJSONObject("key").getString("iKey"));
            key.put("eKey", injectedObject.getJSONObject("key").getString("eKey"));
            tenant.put("key", key);

            JSONObject application = injectedObject.getJSONObject("application");

            if (injectedObject.has("package")) {
                application.put("package_acl", injectedObject.getJSONObject("package").get("acl")); // can be null
                application.put("package_acl_all_env", injectedObject.getJSONObject("package").get("acl_all_env")); // can b null
            }
            tenant.put("application", application);
            soajs.put("tenant", tenant);

            soajs.put("urac", injectedObject.get("urac")); // can be null
            soajs.put("servicesConfig", injectedObject.getJSONObject("key").getJSONObject("config"));
            soajs.put("device", injectedObject.getString("device"));
            soajs.put("geo", injectedObject.getJSONObject("geo"));
            soajs.put("awareness", injectedObject.getJSONObject("awareness"));
        }

        return soajs;
    }

    /**
     * filter 'soajsinjectobj' sent in header
     *
     * @param request
     * @return JSONObject
     */
    private JSONObject mapInjectedObject(ContainerRequest request) {

        JSONObject output = new JSONObject();

        try {
            String soajsinjectobjAsString = request.getHeaderValue("soajsinjectobj");
            JSONObject input = new JSONObject(soajsinjectobjAsString);

            if (input.has("tenant")) {
                JSONObject tenant = input.getJSONObject("tenant");

                String[] variables = {"id", "code"};
                String[] variablesTypes = {"string", "string"};
                JSONObject filteredTenant = filterObject(tenant, variables, variablesTypes, null);

                output.put("tenant", filteredTenant);
            }

            if (input.has("key")) {
                JSONObject key = input.getJSONObject("key");

                String[] variables = {"config", "iKey", "eKey"};
                String[] variablesTypes = {"object", "string", "string"};
                JSONObject filteredKey = filterObject(key, variables, variablesTypes, null);

                output.put("key", filteredKey);
            }

            if (input.has("application")) {
                JSONObject application = input.getJSONObject("application");

                String[] variables = {"product", "package", "appId", "acl", "acl_all_env"};
                String[] variablesTypes = {"string", "string", "string", "object", "object"};
                String[] defaultIfDoesntExist = {"notForMe", "notForMe", "notForMe", null, null};
                JSONObject filteredApplication = filterObject(application, variables, variablesTypes, defaultIfDoesntExist);

                output.put("application", filteredApplication);
            }

            if (input.has("package")) {
                JSONObject pack = input.getJSONObject("package");

                String[] variables = {"acl", "acl_all_env"};
                String[] variablesTypes = {"object", "object"};
                String[] defaultIfDoesntExist = {null, null};
                JSONObject filteredPack = filterObject(pack, variables, variablesTypes, defaultIfDoesntExist);

                output.put("package", filteredPack);
            }

            if (input.has("device")) {
                String device = input.getString("device");
                output.put("device", device);
            } else {
                output.put("device", "");
            }

            if (input.has("geo")) {
                JSONObject geo = input.getJSONObject("geo");
                output.put("geo", geo);
            } else {
                output.put("geo", new JSONObject());
            }

            if (input.has("urac")) {
                JSONObject urac = input.getJSONObject("urac");
                output.put("urac", urac);
            } else {
                output.put("urac", JSONObject.NULL);
            }

            if (input.has("awareness")) {
                JSONObject awareness = input.getJSONObject("awareness");

                String[] variables = {"host", "port"};
                String[] variablesTypes = {"string", "int"};
                String[] defaultIfDoesntExist = {"", ""};
                JSONObject filteredAwareness = filterObject(awareness, variables, variablesTypes, defaultIfDoesntExist);

                output.put("awareness", filteredAwareness);
            } else {
                output.put("awareness", new JSONObject());
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return output;
        }
    }

    /**
     * Return a JSONObject which will have only the variables sent in variables
     * if the variable was not found & defaultIfDoesntExist is not null, the
     * variable will be defaulted to the value sent or ignored if the value sent
     * is 'notForMe'
     *
     * @param input
     * @param variables
     * @param variablesTypes
     * @param defaultIfDoesntExist
     * @return JSONObject
     */
    private JSONObject filterObject(JSONObject input, String variables[], String variablesTypes[], String defaultIfDoesntExist[]) {

        JSONObject output = new JSONObject();

        for (int i = 0; i < variables.length; i++) {
            if (input.has(variables[i])) {
                if (variablesTypes[i].equalsIgnoreCase("object")) {
                    output.put(variables[i], input.getJSONObject(variables[i]));
                } else if (variablesTypes[i].equalsIgnoreCase("int")) {
                    output.put(variables[i], input.getInt(variables[i]));
                } else {
                    output.put(variables[i], input.getString(variables[i]));
                }
            } else if (defaultIfDoesntExist != null) {

                if (defaultIfDoesntExist[i] == null) {
                    output.put(variables[i], JSONObject.NULL);
                } else if (!defaultIfDoesntExist[i].equalsIgnoreCase("notForMe")) {
                    output.put(variables[i], defaultIfDoesntExist[i]);
                }

            }
        }
        return output;
    }
}

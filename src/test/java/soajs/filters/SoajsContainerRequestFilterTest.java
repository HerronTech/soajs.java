/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.WebApplication;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Etienne
 */
public class SoajsContainerRequestFilterTest {
    
    private static final String SOAJS_INJECTED_OBJ = "{\"tenant\":{\"id\":\"5551aca9e179c39b760f7a1a\",\"code\":\"DBTN\"},\"key\":{\"config\":{\"mail\":{\"from\":\"soajstest@soajs.org\",\"transport\":{\"type\":\"smtp\",\"options\":{\"host\":\"secure.emailsrvr.com\",\"port\":\"587\",\"ignoreTLS\":true,\"secure\":false,\"auth\":{\"user\":\"soajstest@soajs.org\",\"pass\":\"p@ssw0rd\"}}}},\"oauth\":{\"loginMode\":\"urac\"}},\"iKey\":\"38145c67717c73d3febd16df38abf311\",\"eKey\":\"d44dfaaf1a3ba93adc6b3368816188f96134dfedec7072542eb3d84ec3e3d260f639954b8c0bc51e742c1dff3f80710e3e728edb004dce78d82d7ecd5e17e88c39fef78aa29aa2ed19ed0ca9011d75d9fc441a3c59845ebcf11f9393d5962549\"},\"application\":{\"product\":\"DSBRD\",\"package\":\"DSBRD_MAIN\",\"appId\":\"5512926a7a1f0e2123f638de\"},\"package\":{},\"device\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36\",\"geo\":{\"ip\":\"127.0.0.1\"},\"awareness\":{\"host\":\"127.0.0.1\",\"port\":4000},\"urac\":{\"_id\": \"59a538becc083eecf37149df\", \"username\": \"owner\", \"firstName\": \"owner\", \"lastName\": \"owner\", \"email\": \"owner@soajs.org\", \"groups\": [ \"owner\" ], \"tenant\": { \"id\":\"5551aca9e179c39b760f7a1a\", \"code\": \"DBTN\" },\"profile\": {},\"acl\": null, \"acl_AllEnv\": null},\"param\":{\"id\":\"5551aca9e179c39b760f7a1a\"}}";
    private static final String SOAJS_INJECTED_OBJ_MISSING_SOME = "{\"tenant\":{\"id\":\"5551aca9e179c39b760f7a1a\",\"code\":\"DBTN\"},\"key\":{\"config\":{\"mail\":{\"from\":\"soajstest@soajs.org\",\"transport\":{\"type\":\"smtp\",\"options\":{\"host\":\"secure.emailsrvr.com\",\"port\":\"587\",\"ignoreTLS\":true,\"secure\":false,\"auth\":{\"user\":\"soajstest@soajs.org\",\"pass\":\"p@ssw0rd\"}}}},\"oauth\":{\"loginMode\":\"uracX\"}},\"iKey\":\"38145c67717c73d3febd16df38abf311\",\"eKey\":\"d44dfaaf1a3ba93adc6b3368816188f96134dfedec7072542eb3d84ec3e3d260f639954b8c0bc51e742c1dff3f80710e3e728edb004dce78d82d7ecd5e17e88c39fef78aa29aa2ed19ed0ca9011d75d9fc441a3c59845ebcf11f9393d5962549\"},\"application\":{\"product\":\"DSBRD\",\"package\":\"DSBRD_MAIN\",\"appId\":\"5512926a7a1f0e2123f638de\"},\"package\":{},\"deviceX\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36\",\"geoX\":{\"ip\":\"127.0.0.1\"},\"awarenessX\":{\"host\":\"127.0.0.1\",\"port\":4000},\"uracY\":{\"_id\": \"59a538becc083eecf37149df\", \"username\": \"owner\", \"firstName\": \"owner\", \"lastName\": \"owner\", \"email\": \"owner@soajs.org\", \"groups\": [ \"owner\" ], \"tenant\": { \"id\":\"5551aca9e179c39b760f7a1a\", \"code\": \"DBTN\" },\"profile\": {},\"acl\": null, \"acl_AllEnv\": null},\"param\":{\"id\":\"5551aca9e179c39b760f7a1a\"}}";
    
    public SoajsContainerRequestFilterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("SOAJS_REGISTRY_API", "anywilldo");
        map.put("SOAJS_ENV", "anywilldo");
        try {
            setEnvironmentVariable(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public static void setEnvironmentVariable(Map<String, String> newenv) throws Exception {
        Class[] classes = Collections.class.getDeclaredClasses();
        Map<String, String> env = System.getenv();
        for (Class cl : classes) {
            if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                Field field = cl.getDeclaredField("m");
                field.setAccessible(true);
                Object obj = field.get(env);
                Map<String, String> map = (Map<String, String>) obj;
                map.clear();
                map.putAll(newenv);
            }
        }
    }

    /**
     * Test of filter method, of class SoajsContainerRequestFilter.
     */
    @Test
    public void testFilter() {
        System.out.println("filter");

        WebApplication webApp = mock(WebApplication.class, RETURNS_MOCKS);
        String method = "GET";

        JSONObject soajsinjectobj = new JSONObject(SOAJS_INJECTED_OBJ);

        InBoundHeaders headers = new InBoundHeaders();
        headers.add("soajsinjectobj", soajsinjectobj.toString());

        ContainerRequest request = new ContainerRequest(webApp, method, URI.create("http://www.example.com/"),
                URI.create("http://www.example.com/"), headers, new ByteArrayInputStream(new byte[0]));

        SoajsContainerRequestFilter instance = new SoajsContainerRequestFilter();
        boolean expResult = false;
        boolean result = instance.filter(request) == null;
        assertEquals(expResult, result);
        
        JSONObject soajsinjectobjmissingsome = new JSONObject(SOAJS_INJECTED_OBJ_MISSING_SOME);

        InBoundHeaders headers2 = new InBoundHeaders();
        headers2.add("soajsinjectobj", soajsinjectobjmissingsome.toString());

        ContainerRequest request2 = new ContainerRequest(webApp, method, URI.create("http://www.example.com/"),
                URI.create("http://www.example.com/"), headers2, new ByteArrayInputStream(new byte[0]));

        SoajsContainerRequestFilter instance2 = new SoajsContainerRequestFilter();
        boolean expResult2 = false;
        boolean result2 = instance2.filter(request2) == null;
        assertEquals(expResult2, result2);
    }

}

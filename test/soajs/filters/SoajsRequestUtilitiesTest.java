/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Etienne
 */
public class SoajsRequestUtilitiesTest {
    
    public SoajsRequestUtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    /**
     * Test of getHost method, of class SoajsRequestUtilities.
     */
    @Test
    public void testGetHost_JSONObject() {
        System.out.println("getHost");
        
        JSONObject soajs = new JSONObject();
        JSONObject awareness = new JSONObject();
        awareness.put("host", "127.0.0.1");
        awareness.put("port", 5000);
        
        soajs.put("awareness", awareness);
        
        String expResult = "127.0.0.1:5000/";
        String result = SoajsRequestUtilities.getHost(soajs);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHost method, of class SoajsRequestUtilities.
     */
    @Test
    public void testGetHost_JSONObject_String() {
        System.out.println("getHost");
        
        JSONObject soajs = new JSONObject();
        JSONObject awareness = new JSONObject();
        awareness.put("host", "127.0.0.1");
        awareness.put("port", 5000);
        
        soajs.put("awareness", awareness);
        
        String serviceName = "controller";
        String expResult = "127.0.0.1:5000/";
        String result = SoajsRequestUtilities.getHost(soajs, serviceName);
        assertEquals(expResult, result);
        
        String serviceName2 = "notController";
        String expResult2 = "127.0.0.1:5000/notController/";
        String result2 = SoajsRequestUtilities.getHost(soajs, serviceName2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getHost method, of class SoajsRequestUtilities.
     */
    @Test
    public void testGetHost_3args() {
        System.out.println("getHost");
        
        JSONObject soajs = new JSONObject();
        JSONObject awareness = new JSONObject();
        awareness.put("host", "127.0.0.1");
        awareness.put("port", 5000);
        
        soajs.put("awareness", awareness);
        
        String serviceName = "controller";
        String expResult = "127.0.0.1:5000/";
        String version = "3";
        String result = SoajsRequestUtilities.getHost(soajs, serviceName,version);
        assertEquals(expResult, result);
        
        String serviceName2 = "notController";
        String expResult2 = "127.0.0.1:5000/notController/v3/";
        String version2 = "3";
        String result2 = SoajsRequestUtilities.getHost(soajs, serviceName2,version2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of merge method, of class SoajsRequestUtilities.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        JSONObject obj1 = null;
        JSONObject obj2 = new JSONObject();
        
        boolean expResult = true;
        boolean result = SoajsRequestUtilities.merge(obj1, obj2) != null;
        
        assertEquals(expResult, result);
        
        boolean expResult2 = true;
        boolean result2 = SoajsRequestUtilities.merge(obj2, obj1) != null;
        
        assertEquals(expResult2, result2);
    }
    
}
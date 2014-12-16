/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import entities.WebUser;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saeed Ahmadi
 */
public class WebUserFacadeTest {
    
    public WebUserFacadeTest() {
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
     * Test of create method, of class WebUserFacade.
     */
    
    /**
     * Test of find method, of class WebUserFacade.
     */
    @Test
    public void testFind_String_String() throws Exception {
        System.out.println("find");
        String username = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        WebUserFacade instance = (WebUserFacade)container.getContext().lookup("java:global/classes/WebUserFacade");
        WebUser expResult = null;
        WebUser result = instance.find(username, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

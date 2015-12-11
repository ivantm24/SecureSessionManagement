/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ManageBean.MbLogin;
import ManageBean.MbSession;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivantactukmercado
 */
public class TestSecureSessionManagemt {
    
    public TestSecureSessionManagemt() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testLoginCorrect() throws NamingException {  
         EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
         MbLogin log;
         log=(MbLogin)container.getContext().lookup("java:global/classes/ManageBean/MbLogin");
         log.setUser("ivanC");
         log.setPassword("123");         
         assertEquals(log.login(), "ManageBankAccount");
         container.close();
     }
     
     @Test
     public void testLoginIncorrect() {
         MbLogin log;
         log=new MbLogin();
         log.setUser("ivanC");
         log.setPassword("1234");         
         assertEquals(log.login(), "index");
     }
     
     @Test
     public void testLogOut() {
         MbLogin log;
         log=new MbLogin();
         log.setUser("ivanC");
         log.setPassword("123");
         log.login();
         MbSession ses=new MbSession();
         ses.closeSession();
         assertNull(ses.getRole());
     }
     
}

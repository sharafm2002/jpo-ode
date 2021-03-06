package us.dot.its.jpo.ode.rsuHealth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import us.dot.its.jpo.ode.heartbeat.RsuHealthController;

public class RsuHealthControllerTest {

    @Test
    public void shouldRefuseConnectionNullIp() {
        
        String testIp = null;
        String testOid = "1.1";
        
        String response = null;
        
        try {
            response = RsuHealthController.heartBeat(testIp, testOid);
            fail("Expected IllegalArgumentException");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
    
    
    @Test
    public void shouldRefuseConnectionNullOid() {
        
        
        String testIp = "127.0.0.1";
        String testOid = null;
        
        String response = null;
        
        try {
            response = RsuHealthController.heartBeat(testIp, testOid);
            fail("Expected IllegalArgumentException");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        
    }

}

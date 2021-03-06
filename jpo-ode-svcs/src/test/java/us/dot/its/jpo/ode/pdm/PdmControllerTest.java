package us.dot.its.jpo.ode.pdm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.GenericAddress;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import us.dot.its.jpo.ode.ManagerAndControllerServices;
import us.dot.its.jpo.ode.eventlog.EventLogger;
import us.dot.its.jpo.ode.plugin.RoadSideUnit.RSU;
import us.dot.its.jpo.ode.plugin.j2735.pdm.J2735ProbeDataManagement;
import us.dot.its.jpo.ode.plugin.j2735.pdm.PDM;
import us.dot.its.jpo.ode.snmp.SnmpProperties;
import us.dot.its.jpo.ode.util.JsonUtils;
import java.text.ParseException;
@RunWith(JMockit.class)
public class PdmControllerTest {

    @Tested
    PdmController testPdmController;
    
    @Mocked J2735ProbeDataManagement mockPdm;
    
    @Mocked ManagerAndControllerServices mockPdmManagerService;
    
    @Injectable RSU mockRsu;
    @Mocked ResponseEvent mockResponseEvent;

    @Test
    public void nullRequestShouldLogAndThrowException(@Mocked final EventLogger eventLogger) {

        try {
            testPdmController.pdmMessage(null);
            fail("Expected PdmException");
        } catch (Exception e) {
            assertEquals(PdmException.class, e.getClass());
            assertEquals("PDM CONTROLLER - Endpoint received null request", e.getMessage());
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
            }
        };
    }

    @Test
    public void nullResponseShouldLogAndReturn(@Mocked final JsonUtils jsonUtils) {

        new Expectations() {
            {
                JsonUtils.fromJson(anyString, J2735ProbeDataManagement.class);
                result = mockPdm;
                
                mockPdm.getRsuList();
                result = new RSU[]{mockRsu};
                
                ManagerAndControllerServices.createAndSend((PDM)any, (SnmpProperties)any);
                result = null;
            }
        };

        try {
            assertNotNull(testPdmController.pdmMessage("testMessage123"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
            }
        };
    }
    
    @Test
    public void nullGetResponseShouldLogAndReturn(@Mocked final JsonUtils jsonUtils) {

        new Expectations() {
            {
                JsonUtils.fromJson(anyString, J2735ProbeDataManagement.class);
                result = mockPdm;
                
                mockPdm.getRsuList();
                result = new RSU[]{mockRsu};
                
                ManagerAndControllerServices.createAndSend((PDM)any, (SnmpProperties)any);
                result = mockResponseEvent;
                
                mockResponseEvent.getResponse();
                result = null;
            }
        };

        try {
            assertNotNull(testPdmController.pdmMessage("testMessage123"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
            }
        };
    }
    
    @Test
    public void shouldLogSuccessWhenErrorStatus0(@Mocked final JsonUtils jsonUtils) {

        new Expectations() {
            {
                JsonUtils.fromJson(anyString, J2735ProbeDataManagement.class);
                result = mockPdm;
                
                mockPdm.getRsuList();
                result = new RSU[]{mockRsu};
                
                ManagerAndControllerServices.createAndSend((PDM)any, (SnmpProperties)any);
                result = mockResponseEvent;
                
                mockResponseEvent.getResponse().getErrorStatus();
                result = 0;
            }
        };

        try {
            assertNotNull(testPdmController.pdmMessage("testMessage123"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
            }
        };
    }
    
    @Test
    public void shouldLogFailureWhenErrorStatusNot0(@Mocked final JsonUtils jsonUtils) {

        new Expectations() {
            {
                JsonUtils.fromJson(anyString, J2735ProbeDataManagement.class);
                result = mockPdm;
                
                mockPdm.getRsuList();
                result = new RSU[]{mockRsu};
                
                ManagerAndControllerServices.createAndSend((PDM)any, (SnmpProperties)any);
                result = mockResponseEvent;
                
                mockResponseEvent.getResponse().getErrorStatus();
                result = 17;
            }
        };

        try {
            assertNotNull(testPdmController.pdmMessage("testMessage123"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
                mockResponseEvent.getResponse().getErrorStatusText();
                times = 1;
            }
        };
    }
    
    @Test
    public void shouldCatchParseExceptionAndLog(@Mocked final JsonUtils jsonUtils, @Mocked final GenericAddress genericAddress) {

        new Expectations() {
            {
                JsonUtils.fromJson(anyString, J2735ProbeDataManagement.class);
                result = mockPdm;
                
                mockPdm.getRsuList();
                result = new RSU[]{mockRsu};
                
                GenericAddress.parse(anyString);
                result = new ParseException("testException123", anyInt);
            }
        };

        try {
            assertNotNull(testPdmController.pdmMessage("testMessage123"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        new Verifications() {
            {
                EventLogger.logger.info(anyString);
            }
        };
    }

}

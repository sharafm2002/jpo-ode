package us.dot.its.jpo.ode.dds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.websocket.Session;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import us.dot.its.jpo.ode.OdeProperties;
import us.dot.its.jpo.ode.dds.DdsRequestManager.DdsRequestManagerException;
import us.dot.its.jpo.ode.model.OdeDepRequest;
import us.dot.its.jpo.ode.model.OdeRequest;
import us.dot.its.jpo.ode.model.OdeRequest.DataSource;
import us.dot.its.jpo.ode.wrapper.WebSocketEndpoint;
import us.dot.its.jpo.ode.wrapper.WebSocketEndpoint.WebSocketException;
import us.dot.its.jpo.ode.wrapper.WebSocketMessageDecoder;
import us.dot.its.jpo.ode.wrapper.WebSocketMessageHandler;

public class DdsDepositRequestManagerTest {

    @Injectable
    DdsDepositRequestManager mockDdsDepositRequestManager;

    @Mocked
    OdeProperties mockOdeProperties;

    @Before
    public void setupOdePropertiesExpectations() {
        new Expectations() {
            {
                mockOdeProperties.getDdsCasUrl();
                result = anyString;
                minTimes = 0;
                mockOdeProperties.getDdsCasUsername();
                result = anyString;
                minTimes = 0;
                mockOdeProperties.getDdsCasPassword();
                result = anyString;
                minTimes = 0;
                mockOdeProperties.getDdsWebsocketUrl();
                result = anyString;
                minTimes = 0;
            }
        };
    }

    /**
     * Verify the constructor populates odeProperties and ddsClient
     */
    @Test
    public void shouldConstruct() {

        assertNotNull(mockDdsDepositRequestManager.getOdeProperties());
        assertNotNull(mockDdsDepositRequestManager.getDdsClient());
    }

    /**
     * Verify DdsRequestManagerException thrown when .getRequestType() returns
     * null
     */
    @Test
    public void shouldFailToBuildEncodeTypeNull(@Mocked OdeDepRequest mockOdeDepRequest) {

        new Expectations(StringUtils.class) {
            {
                StringUtils.lowerCase(anyString);
                result = null;
            }
        };

        new Expectations() {
            {
                mockOdeDepRequest.getEncodeType();
                result = null;
            }
        };

        DdsDepositRequestManager mockDdsDepositRequestManager = null;
        try {
            mockDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception: " + e);
        }

        try {
            DdsRequest testDdsRequest = mockDdsDepositRequestManager.buildDdsRequest(mockOdeDepRequest);
            fail("Expected DdsRequestManagerException");
        } catch (Exception e) {
            assertEquals(DdsRequestManagerException.class, e.getClass());
            assertTrue(e.getMessage().startsWith("Invalid or unsupported EncodeType Deposit:"));
        }
    }

    @Test
    public void shouldBuildSuccessfully(@Mocked OdeDepRequest mockOdeDepRequest) {

        new Expectations() {
            {
                mockOdeDepRequest.getEncodeType();
                result = "base64";
            }
        };

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception: " + e);
        }

        DdsDepRequest actualDdsRequest = null;
        try {
            actualDdsRequest = (DdsDepRequest) testDdsDepositRequestManager.buildDdsRequest(mockOdeDepRequest);
            assertNotNull("Failed to build actualDdsRequest", actualDdsRequest);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        assertEquals("base64", actualDdsRequest.getEncodeType());
    }

    @Test
    public void connectShouldTryToLoginAndConnect(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession) {

        try {

            new Expectations() {
                {
                    mockDdsClient.login(withAny(DdsStatusMessageDecoder.class), (StatusMessageHandler) any);
                    result = mockWsClient;

                    mockWsClient.connect();
                    result = mockSession;
                }
            };
        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        Session actualSession = null;
        try {
            actualSession = testDdsDepositRequestManager.connect(mockWebSocketMessageHandler,
                    mockWebSocketMessageDecoder.getClass());
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception attempting to connect: " + e);
        }

        assertEquals("Sessions do not match", mockSession, actualSession);
        assertTrue("Expected isConnected() to return true", testDdsDepositRequestManager.isConnected());
    }

    @Test
    public void connectShouldRethrowLoginException(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession) {

        try {

            new Expectations() {
                {
                    mockDdsClient.login(withAny(DdsStatusMessageDecoder.class), (StatusMessageHandler) any);
                    result = mockWsClient;

                    mockWsClient.connect();
                    result = new DdsRequestManagerException("test exception");
                }
            };
        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        Session actualSession = null;

        try {
            actualSession = testDdsDepositRequestManager.connect(mockWebSocketMessageHandler,
                    mockWebSocketMessageDecoder.getClass());
            fail("Expected DdsRequestManagerException");
        } catch (DdsRequestManagerException e) {
            assertEquals(DdsRequestManagerException.class, e.getClass());
            assertTrue(e.getMessage().startsWith("Error connecting to DDS"));
        }
    }

    @Test
    public void connectShouldSetConnectedFalseWhenSessionNull(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession) {

        try {

            new Expectations() {
                {
                    mockDdsClient.login(withAny(DdsStatusMessageDecoder.class), (StatusMessageHandler) any);
                    result = mockWsClient;

                    mockWsClient.connect();
                    result = null;
                }
            };
        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        Session actualSession = null;

        try {
            actualSession = testDdsDepositRequestManager.connect(mockWebSocketMessageHandler,
                    mockWebSocketMessageDecoder.getClass());
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception attempting to connect: " + e);
        }

        assertFalse(testDdsDepositRequestManager.isConnected());
    }

    @Test
    public void sendRequestshouldThrowExceptionWhenSessionNull(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession,
            @Mocked OdeRequest mockOdeRequest) {

        try {

            new Expectations() {
                {
                    // mockDdsClient.login(withAny(DdsStatusMessageDecoder.class),
                    // (StatusMessageHandler) any);
                    // result = mockWsClient;

                    mockWsClient.connect();
                    result = null;
                }
            };
        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        // Verify the session is null or the next part will be skipped
        assertNull(testDdsDepositRequestManager.getSession());

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        try {
            testDdsDepositRequestManager.sendRequest(mockOdeRequest);
            fail("Expected DdsRequestManagerException");
        } catch (DdsRequestManagerException e) {
            assertEquals("Incorrect exception type", DdsRequestManagerException.class, e.getClass());
            assertTrue("Unexpected exception message" + e.getMessage(),
                    e.getMessage().startsWith("Error sending Data Request"));
            assertFalse("Expected connected to be false", testDdsDepositRequestManager.isConnected());
        }
    }

    @Test
    public void sendRequestShouldSendWithoutError(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession,
            @Mocked OdeDepRequest mockOdeDepRequest) {

        try {

            new Expectations() {
                {
                    mockWsClient.connect();
                    result = mockSession;

                    mockWsClient.send(anyString);
                }
            };

            new Expectations() {
                {
                    mockOdeDepRequest.getDataSource();
                    result = DataSource.SDC;
                    mockOdeDepRequest.getEncodeType();
                    result = "base64";
                }
            };

        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        // Verify the session is null or the next part will be skipped
        assertNull(testDdsDepositRequestManager.getSession());

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        try {
            testDdsDepositRequestManager.sendRequest(mockOdeDepRequest);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in send request: " + e);
        }

    }

    @Test
    public void sendRequestShouldLogWhenErrorClosingClient(@Mocked DdsClient<DdsStatusMessage> mockDdsClient,
            @Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked WebSocketMessageHandler<DdsStatusMessage> mockWebSocketMessageHandler,
            @Mocked WebSocketMessageDecoder<?> mockWebSocketMessageDecoder, @Mocked Session mockSession,
            @Mocked OdeRequest mockOdeRequest, @Mocked Logger mockLogger) {

        try {

            new Expectations() {
                {
                    mockWsClient.connect();
                    result = mockSession;

                    mockWsClient.close();
                    result = new WebSocketException("test exception on .close()");
                }
            };

            new Expectations() {
                {
                    mockLogger.error(anyString, (WebSocketException) any);
                }
            };
        } catch (Exception e) {
            fail("Unexpected exception mocking expectations: " + e);
        }

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);
            testDdsDepositRequestManager.setLogger(mockLogger);
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        // Verify the session is null or the next part will be skipped
        assertNull(testDdsDepositRequestManager.getSession());

        testDdsDepositRequestManager.setDdsClient(mockDdsClient);
        testDdsDepositRequestManager.setWsClient(mockWsClient);

        try {
            testDdsDepositRequestManager.sendRequest(mockOdeRequest);
            fail("Expected DdsRequestManagerException");
        } catch (Exception e) {
            assertEquals(DdsRequestManagerException.class, e.getClass());
        }
    }

    @Test
    public void closeShouldCloseSuccessfully(@Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked Logger mockLogger) {

        try {
            new Expectations() {
                {
                    mockWsClient.close();
                }
            };
        } catch (WebSocketException e1) {
            fail("Unexpected exception calling close on mock websocket client: " + e1);
        }

        new Expectations() {
            {
                mockLogger.info(anyString);
            }
        };

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);

        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        testDdsDepositRequestManager.setWsClient(mockWsClient);
        testDdsDepositRequestManager.setLogger(mockLogger);

        try {
            testDdsDepositRequestManager.close();
        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception calling close: " + e);
        }

        assertFalse("Expected connected to be false.", testDdsDepositRequestManager.isConnected());
        assertNull("Expected wsClient to be null.", testDdsDepositRequestManager.getWsClient());
        assertNull("Expected session to be null.", testDdsDepositRequestManager.getSession());

    }

    @Test
    public void closeShouldThrowException(@Mocked WebSocketEndpoint<DdsStatusMessage> mockWsClient,
            @Mocked Logger mockLogger) {

        try {
            new Expectations() {
                {
                    mockWsClient.close();
                    result = new WebSocketException("test WebSocketException on close method");
                }
            };
        } catch (WebSocketException e1) {
            fail("Unexpected exception websocket client expectations: " + e1);
        }

        new Expectations() {
            {
                mockLogger.info(anyString);
            }
        };

        DdsDepositRequestManager testDdsDepositRequestManager = null;
        try {
            testDdsDepositRequestManager = new DdsDepositRequestManager(mockOdeProperties);

        } catch (DdsRequestManagerException e) {
            fail("Unexpected exception in DdsDepositRequestManager constructor: " + e);
        }

        testDdsDepositRequestManager.setWsClient(mockWsClient);
        testDdsDepositRequestManager.setLogger(mockLogger);

        try {
            testDdsDepositRequestManager.close();
            fail("Expected DdsRequestManagerException to be thrown in close.");
        } catch (Exception e) {
            assertEquals("Incorrect exception thrown", DdsRequestManagerException.class, e.getClass());
            assertTrue("Incorrect error message: " + e.getMessage(),
                    e.getMessage().startsWith("Error closing DDS Client"));
        }

    }
}

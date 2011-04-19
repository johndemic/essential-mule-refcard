package com.mulesoft.refcard;

import org.mule.tck.FunctionalTestCase;

import java.util.Date;


public class HeartBeatFunctionalTestCase extends FunctionalTestCase {

    Date now = new Date();

    @Override
    protected String getConfigResources() {
        return "heartbeat-config.xml";
    }

    public void testCanReceiveHeartBeat() throws Exception {
        /*
        ToDo Fix this test to assert the heartbeat is received

        MuleClient client = new MuleClient(muleContext);
        MuleMessage result = client.request("vm://heartbeat", 15000);
        assertNotNull(result);
        assertTrue((Long) result.getPayload() > now.getTime());    */
    }
}

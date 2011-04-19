package com.mulesoft.refcard;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;


public class DynamicRoutingConfigFunctionalTestCase extends FunctionalTestCase {

    @Override
    protected String getConfigResources() {
        return "dlq-config.xml";
    }


    public void testCanRouteToEndpointDynamically() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        MuleMessage message = new DefaultMuleMessage("Hello, world.", muleContext);
        message.setOutboundProperty("destination-queue", "foo");
        client.dispatch("vm://input", message);

        MuleMessage result = client.request("vm://foo", 15000);

        assertEquals("Hello, world.", result.getPayloadAsString());
    }

    public void testCanRouteToDeadLetterQueue() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        MuleMessage message = new DefaultMuleMessage("Hello, world.", muleContext);
        client.dispatch("vm://input", message);

        MuleMessage result = client.request("vm://dlq", 15000);
        assertNotNull(result);
    }
}

package com.mulesoft.refcard;

import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

public class XPathFilterFunctionalTestCase extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "xpath-filter-config.xml";
    }

    public void testMessageNotFiltered() throws Exception {
        String xml = "<order><zipCode>11209</zipCode></order>";

        MuleClient client = new MuleClient(muleContext);
        client.dispatch("vm://input", xml, null);

        assertNotNull(client.request("vm://output", 15000).getPayloadAsString());
    }

    public void testMessageIsFiltered() throws Exception {
        String xml = "<order><zipCode>11210</zipCode></order>";

        MuleClient client = new MuleClient(muleContext);
        client.dispatch("vm://input", xml, null);

        assertNull(client.request("vm://output", 5000));
    }
}

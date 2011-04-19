package com.mulesoft.refcard;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

public class RestServiceFunctionalTestCase extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "rest-service-config.xml";
    }

    public void testCanConsumeService() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        MuleMessage response = client.send("http://localhost:8080/rest/random-number",null,null);
        assertNotNull(response);

    }
}

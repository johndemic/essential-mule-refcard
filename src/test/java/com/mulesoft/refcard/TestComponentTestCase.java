package com.mulesoft.refcard;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

public class TestComponentTestCase extends FunctionalTestCase {

    @Override
    protected String getConfigResources() {
        return "test-component-config.xml";
    }

    public void testCanInvokeTestComponent() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        MuleMessage response = client.send("vm://test", "Foo", null);
        assertEquals("FooFoo", response.getPayloadAsString());
    }
}

package com.mulesoft.refcard;

import org.mule.api.MuleMessage;
import org.mule.message.ExceptionMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;


public class DeadLetterQueueFunctionalTestCase extends FunctionalTestCase {

    @Override
    protected String getConfigResources() {
        return "dlq-config.xml";
    }

    public void testErrorsAreSentToDeadLetterQueue() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        client.dispatch("vm://input", "foo",null);

        MuleMessage result = client.request("vm://dlq", 15000);

        assertTrue(result.getPayload() instanceof ExceptionMessage);

    }
}

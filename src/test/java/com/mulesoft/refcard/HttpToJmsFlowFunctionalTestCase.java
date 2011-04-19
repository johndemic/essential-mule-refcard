package com.mulesoft.refcard;


import org.hornetq.core.config.impl.FileConfiguration;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

public class HttpToJmsFlowFunctionalTestCase extends FunctionalTestCase {

    FileConfiguration configuration;
    HornetQServer server;

    @Override
    protected String getConfigResources() {
        return "spring-config.xml,http-to-jms-config.xml";
    }

    @Override
    protected void suitePreSetUp() throws Exception {

        configuration = new FileConfiguration();
        configuration.setConfigurationUrl("hornetq-configuration.xml");
        configuration.start();
        server = HornetQServers.newHornetQServer(configuration);
        JMSServerManager jmsServerManager = new JMSServerManagerImpl(server, "hornetq-jms.xml");
        jmsServerManager.setContext(null);
        jmsServerManager.start();
    }

    @Override
    protected void suitePostTearDown() throws Exception {
        configuration.stop();
        server.stop();
    }


    public void testCanPostToJmsQueue() throws Exception {
        MuleClient client = new MuleClient(muleContext);
        client.dispatch("http://localhost:8080/input","foo",null);

        MuleMessage result = client.request("jms://output", 15000);
        assertNotNull(result);
        assertEquals("foo", result.getPayload());
    }
}

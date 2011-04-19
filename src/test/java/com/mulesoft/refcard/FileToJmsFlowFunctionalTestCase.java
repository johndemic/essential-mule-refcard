package com.mulesoft.refcard;


import org.hornetq.core.config.impl.FileConfiguration;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;
import org.mule.util.FileUtils;

import java.io.File;

public class FileToJmsFlowFunctionalTestCase extends FunctionalTestCase {

    FileConfiguration configuration;
    HornetQServer server;

    @Override
    protected String getConfigResources() {
        return "spring-config.xml,file-to-jms-flow-config.xml";
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

    @Override
    protected void doSetUp() throws Exception {
        File inputDirectory = new File("data/files");
        if (!inputDirectory.exists()) {
            inputDirectory.mkdirs();
        }

    }

    public void testCanReceiveJMSMessage() throws Exception {
        String filename = "data/files/data.txt";

        File inputFile = new File(filename);

        if (inputFile.exists()) inputFile.delete();

        FileUtils.writeStringToFile(inputFile, "Foo.");

        MuleClient client = new MuleClient(muleContext);

        MuleMessage message = client.request("jms://output", 15000);

        assertNotNull(message);
        assertEquals("Foo.", message.getPayload());
    }
}

package com.iot.MQTTService;


import java.util.Random;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.iot.CloudAnalytics.*;


public class MQTTServiceMgr implements MqttCallback  {

	//String broker = "tcp://broker.mqttdashboard.com:1883";
	
	String broker = "tcp://broker.emqx.io:1883";
    String clientID = "client1";
	String topic = "/gatewayid/1234xyz76/HealthNet/SensorData";
	MqttClient sampleClient = null;
	 
	 
public MqttClient mqttconnect()
{
	
       MemoryPersistence persistence = new MemoryPersistence();
       Random rand = new Random();
       
       // Generate random integers in range 0 to 999
       int  randID = rand.nextInt(1000);

    //   String clientID = Integer.toString(randID);
      String  clientId = MqttAsyncClient.generateClientId();

     //  if (StringUtil.isEmpty( clientId ) ) {
    	//   clientId = MqttAsyncClient.generateClientId();
    	//  }

       try {
		sampleClient  = new MqttClient(broker, clientId, persistence);
		  MqttConnectOptions connOpts = new MqttConnectOptions();
	       connOpts.setCleanSession(false);
	       connOpts.setUserName("emqx");
	       connOpts.setUserName("public");
	       connOpts.setKeepAliveInterval(1000);
	       connOpts.setConnectionTimeout(500000);
	       System.out.println("Connecting to EMQ broker: " + broker);
	       sampleClient.connect(connOpts);	
	} catch (MqttException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       return sampleClient;
     
	
}

public  void mqttdatapublish(String data)
{


	 String content = null;
	 content=data.toString();
	 int qos = 2;
	 String topic = "gatewayid/1234/BACNet/IoTSensorConfig";
     

     MemoryPersistence persistence = new MemoryPersistence();

     
	 try {
		 
	    MqttClient sampleClient = new MqttClient(broker, clientID, persistence);
	    
	       MqttConnectOptions connOpts = new MqttConnectOptions();
	       connOpts.setCleanSession(true);
	       connOpts.setUserName("emqx");
	       connOpts.setUserName("public");
	       connOpts.setKeepAliveInterval(1000);
	       connOpts.setConnectionTimeout(5000000);
	       
	      // connOpts.s
	    //   sampleClient.
	       System.out.println("Connecting to broker: " + broker);
	       sampleClient.connect(connOpts);	

	     /*   for(int i = 0;i<2;i++)
	        {*/
	        System.out.println("Publishing message: " + data);
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        sampleClient.publish(topic, message);
	       
	        
	        System.out.println("Message published");
	      //  sampleClient.disconnect();
	      //  System.out.println("Disconnected");
	    } catch (MqttException me) {
	        System.out.println("reason " + me.getReasonCode());
	        System.out.println("msg " + me.getMessage());
	        System.out.println("loc " + me.getLocalizedMessage());
	        System.out.println("cause " + me.getCause());
	        System.out.println("excep " + me);
	        me.printStackTrace();
	    }
}

public String mqttdatasubscribe(MqttClient  sampleClient) throws InterruptedException
{
	String broker = "tcp://broker.mqttdashboard.com:8884";
    String clientId = "JavaSample";
	 String topic = "gatewayid/1234/BACNet/IoTSensorConfig";
	 final String responseData = null;

    MemoryPersistence persistence = new MemoryPersistence();
    try {
     if(sampleClient!=null)
     {
	     System.out.println("Triggering subscription");



         sampleClient.subscribe(topic,2);
         sampleClient.setCallback(new MqttCallback() {

            @Override
            public void messageArrived(String topic, MqttMessage msg) throws Exception {
               String  messageBody = new String(msg.getPayload());
               
               System.out.println("Data Recevied "+messageBody);

               sendDataToWizard(messageBody);
               responseData.concat(messageBody);
               GatewaySensorDataReceiver sdata = new GatewaySensorDataReceiver();
               sdata.printLog(messageBody);
                System.out.println(topic);
              //  return;

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            // TODO Auto-generated method stub
            }

            @Override
            public void connectionLost(Throwable exception) {
            // TODO Auto-generated method stub
            	
                try {
					sampleClient.subscribe(topic,2);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                System.out.println("On Connection Lost");
            //    exception.printStackTrace();

                MqttException mqttexcep =  (MqttException)exception;
                System.out.println("reason " + mqttexcep.getReasonCode());
                System.out.println("msg " + mqttexcep.getMessage());
                System.out.println("loc " + mqttexcep.getLocalizedMessage());

            /*	try {
				//	sampleClient.disconnect(5000);
	            //	sampleClient.close();

				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("On Connection Lost");	
              //  sampleClient.connect();*/

                

            }
        });
    }
    }
    catch (MqttException me) {
        System.out.println("reason " + me.getReasonCode());
        System.out.println("msg " + me.getMessage());
        System.out.println("loc " + me.getLocalizedMessage());
        System.out.println("cause " + me.getCause());
        System.out.println("excep " + me);
        me.printStackTrace();
    }
    
	return responseData;
        	
	
	
}

public void mqttreconnect()
{
	
	
	
    System.out.println("On Connection Lost");	
	
}

public void sendDataToWizard(String data )
{
	
	IoTGatewayConfigWizard gwwizard = new IoTGatewayConfigWizard();
	gwwizard.visualizeSensorData(data);
	
}
//Override methods from MqttCallback interface
@Override
public void messageArrived(String topic, MqttMessage message) throws Exception {
      System.out.println("message is : "+message);
  }

@Override
public void connectionLost(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void deliveryComplete(IMqttDeliveryToken arg0) {
	// TODO Auto-generated method stub
	
}
};
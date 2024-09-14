package com.iot.CloudAnalytics;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.iot.MQTTService.MQTTServiceMgr;

public class CloudPublisher extends Thread {
	 String topic = "gatewayid/1234/BACNet/IoTSensorConfig";

	 String broker = "tcp://broker.emqx.io:1883";
	 String clientId = "client1";
	 MqttClient sampleClient = null;
	    public void run()
	    {
	    	
 
	    	{
	    	  mqttdatapublish("Hello Subscriber");
	    	}  	 
	    
	    }
	   
	    public void mqttconnect()
	    {
	    	
	        MemoryPersistence persistence = new MemoryPersistence();

	    	 try {
	    		 
	    	        sampleClient = new MqttClient(broker, clientId, persistence);
	    	    
	    	       MqttConnectOptions connOpts = new MqttConnectOptions();
	    	       connOpts.setCleanSession(true);
	    	       connOpts.setUserName("emqx");
	    	       connOpts.setUserName("public");
	    	       connOpts.setKeepAliveInterval(1000);
	    	       connOpts.setConnectionTimeout(5000);
	    	       System.out.println("Connecting to broker: " + broker);
	    	       sampleClient.connect(connOpts);	
	    	       
	    	       

	    
	    	    } catch (MqttException me) {
	    	        System.out.println("reason " + me.getReasonCode());
	    	        System.out.println("msg " + me.getMessage());
	    	        System.out.println("loc " + me.getLocalizedMessage());
	    	        System.out.println("cause " + me.getCause());
	    	        System.out.println("excep " + me);
	    	        me.printStackTrace();
	    	    }
	    }
	   
	    
	    public  void mqttdatapublish(String data)
	    {


	    
	    	 try {
    	   	       int qos = 1;

	    	        System.out.println("Publishing message: " + data);
	    	        MqttMessage message = new MqttMessage(data.getBytes());
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
	  
	    }
	    /*public static void main(String[] args)
	    {
	    	CloudPublisher pubthread = new CloudPublisher(); // creating thread
	    	pubthread.start(); // starting thread
	    }*/
	    
	    
 


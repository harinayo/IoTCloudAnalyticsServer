package com.iot.CloudAnalytics;



import org.json.JSONObject;  
import org.json.JSONArray;
import org.json.JSONException;

import java.awt.event.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.iot.MQTTService.MQTTAsyncServiceMgr;
import com.iot.MQTTService.MQTTServiceMgr;

import javax.swing.JTextArea;

import org.json.JSONException;
import org.json.JSONObject;

public class IoTGatewayConfigWizard extends JFrame implements ItemListener {
 
    // frame
    static JFrame f  = new JFrame("IoT Gateway Wizard ");
    
    final static JFrame frame = new JFrame("IoT Cloud Server - Device Monitoring ");
    
   
    final static JFrame regFrame = new JFrame("IoT Cloud Analytics Server  ");

    
    static JTextArea Deviceregistrations = new JTextArea(10, 100);  
	
    
    static SensorUXManager displaymgr = new SensorUXManager();

    
    
    static  JTextArea sensordata = new JTextArea(20, 80); 
  

   
    static boolean cascading_anomaly = false;
 
		static  int anomalyevtcount =0;


    static MQTTAsyncServiceMgr  mqttclient = new MQTTAsyncServiceMgr();

    
    static String GatewayRegistrationTopic  = "/IoTRegisterGateway";
    static String SensorRegistrationTopic  = "/IoTRegisterSensor/Include";
    static String SensorDeRegistrationTopic  = "/IoTRegisterSensor/Exclude";
    static String  sensorDataTopic ="/gatewayid/1234xyz76/HealthNet/SensorData";
    static String energyDataTopic ="/gatewayid/1234/Energydata/PeakLoadMonitoring";
    // label
    static JLabel l, l1, l3, l4;
   
    SensorUXManager uxmanager = null;
    // combobox
    static JComboBox c1, c2;
    
   //  static JTextField  jtext1 = new JTextField("                        ");
   //  static JTextField  jtext2 = new JTextField("                        " );
   //  static JTextField  jtext3 = new JTextField("                          " );
   //  static JTextField  jtext4 = new JTextField("                           " );

    // main class
    
    public static void parsedata() throws JSONException
    {
    	 String jsonStr = "{\"rmscurrent\": \"9837\", \"reactivepower\": \"98737\"}";
         // convert to json object
         JSONObject json = new JSONObject(jsonStr);
         // print object
         System.out.println(json.toString());
         // get value for a key
         String brand = json.getString("rmscurrent");
         System.out.println("brand val"+brand);
         
    }
    public static void main(String[] args) throws InterruptedException, JSONException
    {
        // create a new frame
 
        // create a object
        IoTGatewayConfigWizard gateway = new IoTGatewayConfigWizard();
         
        createRegistrationUX();
      //  handleIoTDeviceregistrations();

        createSensorDataStreamUX();
     SensorUXManager uxmanager = new SensorUXManager();
   //   uxmanager.createAnomalyDataManagerUX();
   //     startstreamingdata();
   //     createAnomalyDataManagerUX();
        
        
     

        // array of string containing cities
    /*    String s1[] = { "SmartHome", "HumanbodyMonitoring", "CattleMonitoring", "Industrial Monitoring" };
    
 
        // create checkbox
        c1 = new JComboBox(s1);

        c1.setSelectedIndex(3);
 
        // add ItemListener
        c1.addItemListener(gateway);
 
        // set the checkbox as editable
        c1.setEditable(true);
 
        // create labels
        l = new JLabel("select your Gateway Configuration ");
        l1 = new JLabel("SmartHome  selected");

 
        // set color of text
        l.setForeground(Color.blue);
      
 
        // create a new panel
        JPanel p = new JPanel();
        
       textArea = new JTextArea();
        
        textArea.setBounds(50, 50, 630, 30);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
 
        p.add(l);
        p.add(textArea);
 
        // add combobox to panel
        p.add(c1);
 
     
 //       // set a layout for panel
        p.setLayout(new FlowLayout());
        
        f.add(areaScrollPane);
 
        // add panel to frame
        f.add(p);
 
        // set the size of frame
        f.setSize(500	, 500);
        
       
        f.show();
        
        */
    }
    
    public static void createRegistrationUX()
    {
    	
    	
      /*  JTextArea Text = new JTextArea(5,5);
       // Text.setBounds(1000,600,300,300);
        Text.setOpaque(false);
        Text.setWrapStyleWord(true);
        Text.setLineWrap(true); 
        Text.setBorder(BorderFactory.createLineBorder(Color.BLUE));*/
        
    	//  Deviceregistrations.setB
    	//  Deviceregistrations.setBounds(0, 0, 200, 40);
    	 // sensordata.setBounds(100, 0, 200, 40);

    /*	  JLabel label = new JLabel("Demo Label!");
    	  Dimension size = label.getPreferredSize();
    	  label.setBounds(100, 500, size.width, size.height);*/

           // create a panel
           JPanel p = new JPanel();
//          p.add(label);

          
           JPanel p1 = new JPanel();
    
           // add label and button to panel

           p.add(Deviceregistrations);
           

     	  JLabel lsensordata = new JLabel("Sensor data from IoT gateways ... ");
     	  Dimension ssize = lsensordata.getPreferredSize();
     	 lsensordata.setBounds(100, 1000, ssize.width, ssize.height);
     	 
     	  p.add(lsensordata);
   
         //  p1.add(sensordata);
    
           // set visibility internal frame
          //    devicereg.setVisible(true);
         //  sensordata.setVisible(true);
    
           // add panel to internal frame
          regFrame.add(p);
     //     regFrame.add(p1);

     //     regFrame.add(p1);
   // set the title of the frame
    	 //   in.setTitle("Device Registrations");


      
            
            
      
            JScrollPane scrollableTextArea = new JScrollPane(Deviceregistrations);  
      
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
      
            regFrame.getContentPane().add(scrollableTextArea); 
            
            
            JScrollPane scrollableSensordata = new JScrollPane(sensordata);  
            
            scrollableSensordata.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

            scrollableSensordata.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
            
            scrollableSensordata.setBounds(new Rectangle(50, 50, 200, 40));
            
      
        //    regFrame.getContentPane().add(scrollableSensordata); 
            

    	    regFrame.setSize(500,500);  
            
    	    regFrame.setVisible(true);  
            regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      
            // set flow layout for the frame  
            regFrame.getContentPane().setLayout(new FlowLayout());  
            
            regFrame.show();
         
    }
    public static void  createSensorDataStreamUX()
    {
    	
        
    	// Rectangle bounds = new Rectangle(0, 0, 300, 2000);
    	 // frame.setMaximizedBounds(bounds);
        // Display the window.
    	
    	  Font labelfont = new Font("Verdana", Font.BOLD, 16);
          Font font = new Font("Verdana", Font.BOLD, 14);
       	sensordata.setForeground(Color.BLUE);

    	  sensordata.setFont(labelfont);
    	 startstreamingdata();
        frame.setSize(300,300);  
        
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // set flow layout for the frame  
        frame.getContentPane().setLayout(new FlowLayout());  
        
        
  
        JScrollPane scrollableTextArea = new JScrollPane(sensordata);  
  
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
  
        frame.getContentPane().add(scrollableTextArea); 
        
        
       
        
        startstreamingdata();
    	
    }
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox 1is changed
        if (e.getSource() == c1) {
        	
        	 String gwselected = c1.getSelectedItem().toString();
 
        	 System.out.println("You seleted the Gateway Type : " + gwselected);
             IoTGatewayDBInterface dbinterface  = new IoTGatewayDBInterface();
             GatewayConfigParams gwdata = dbinterface.IoTGatewayReadData(gwselected);
             l1.setText(c1.getSelectedItem() + " selected");
             
             String valueToBeInserted="";
             
             String[] strArray = null ;
             
             DefaultListModel<String> gwconfig = new DefaultListModel<String>();  
     //        gwconfig.addElement("Item1");  
     //        gwconfig.addElement("Item2");  

                gwconfig.addElement(gwdata.GatewayType);
                gwconfig.addElement(gwdata.sensortype);  
             	System.out.println("Adding gateway config to UX  : " + gwdata.sensortype);
                gwconfig.addElement(gwdata.attribute);  
                gwconfig.addElement(Float.toString(gwdata.minval));  
                gwconfig.addElement(Float.toString(gwdata.maxval));  

             //   gwconfig.addElement(gwdata.minval);  
                 	 System.out.println("Adding gateway config to UX  : " + gwdata.minval);
      

                System.out.println("Config data is   : " + valueToBeInserted);

          
                setLayout(new GridBagLayout());

              DefaultTableModel model = new DefaultTableModel(0, 1);
              JTable table = new JTable(model);
              table.setShowGrid(true);
              table.setShowVerticalLines(true);
              
          //    table.getToolTipText(gwdata.GatewayType);
              
              
     		  
           	 String gwtype = "Gateway Configuration for -----  ";
	      	 String gwtypec = gwtype.concat(gwdata.GatewayType);
              model.addRow(new String[]{
            		  gwtypec   
                 });
       //       model.addRow(new String[]{
        //           gwdata.attribute    
         //     });
             
               String minval =null;
      		   String minvaldata = Float.toString(gwdata.minval);
      		  
            	String minvalue = "Minimum value Celsius----  ";
	      		String mval = minvalue.concat(Float.toString(gwdata.minval));
	      		
	      		JLabel mvalue = new JLabel("Test");
	      		mvalue.setText(mval);
	      		mvalue.setBounds(120, 10, 150, 20);
	      		
	      	    
	      	   // create a new panel
	            JPanel p = new JPanel();	 
	            p.add(mvalue);	          
	            p.setLayout(new FlowLayout());
	            // add panel to frame
	            f.add(p);
	            model.addRow(new String[]{
	      	       
	            		mval	            	  
	                 });
	            
	        	String maxvalue = "Maximum  value  Celsius----  ";
	      		String maxv = minvalue.concat(Float.toString(gwdata.maxval));
	      	
	            model.addRow(new String[]{
	            	       
	            		maxv	            	  
	                 });
	        
	            String minanomaly = "Anomaly values ----Minvalue <  ";
	      		String minanomalyval = minanomaly.concat(Float.toString(gwdata.minval));
	      	
	            model.addRow(new String[]{
	            	       
	            		minanomalyval	            	  
	                 });
	            
	            String maxanomaly = "Anomaly values ----Maxvalue >  ";
	      		String maxanomalyval = maxanomaly.concat(Float.toString(gwdata.maxval));
	      	
	            model.addRow(new String[]{
	            	       
	            		maxanomalyval	            	  
	                 });
	            
	            
	         //   DisplayIoTConfig(table);
	            
	        //    f.add(new JScrollPane(table));
	        //    f.setVisible(true);
	            
	          String aggdata = minanomalyval.concat(maxanomalyval); 
             
	                    
        }
 
        // if state of combobox 2 is changed
        else
            l4.setText(c2.getSelectedItem() + " selected");
    }
    
    public static void startstreamingdata()
    {
        String pubtopic = "/IoTGateway/DynamicModel";

    	  
          try {
  			mqttclient.connectToMqttServer();
  		} catch (MqttException excep) {
  			// TODO Auto-generated catch block
  			excep.printStackTrace();
  		}
          
     	 System.out.println("MQTT Connection to broker is successful  : ");

     //     mqttclient.mqttpublish(pubtopic, "Hello Broker");
        mqttclient.mqttsubscribe(sensorDataTopic);
          
       //   mqttclient.mqttsubscribe(energyDataTopic);
          
    }
    public static void pushDataModeltoGateway(String pubtopic , String data )
    {
    	
    	  
          try {
  			mqttclient.connectToMqttServer();
  		} catch (MqttException excep) {
  			// TODO Auto-generated catch block
  			excep.printStackTrace();
  		}
       
          mqttclient.mqttpublish(pubtopic, data);
    	
    }
    public static void handleIoTDeviceregistrations()
    {
    	
    	  try {
    			mqttclient.connectToMqttServer();
    		} catch (MqttException excep) {
    			// TODO Auto-generated catch block
    			excep.printStackTrace();
    		}
    
       //  mqttclient.mqttsubscribe(GatewayRegistrationTopic);

     //    mqttclient.mqttsubscribe(SensorRegistrationTopic);
         
         mqttclient.mqttsubscribe(sensorDataTopic);

    }
    
    public  void sendtomqttbroker(String aggdata) throws InterruptedException
    {
    
        MQTTServiceMgr mqttservicemgr = new MQTTServiceMgr();
     //   String aggdta =data.maxval;
        
      //  mqttservicemgr.mqttdatapublish(aggdata);
        
        
        
        
   	 MqttClient sampleClient = mqttservicemgr.mqttconnect();
     String data =  mqttservicemgr.mqttdatasubscribe(sampleClient);

   //  visualizeSensorData(data);
   //  vehicledataCascadingAnomaly(data);
    }
    
    public  static void Subscribetomqttbroker() throws InterruptedException
    {
    
        MQTTServiceMgr mqttservicemgr = new MQTTServiceMgr();
     //   String aggdta =data.maxval;
        
      //  mqttservicemgr.mqttdatapublish(aggdata);
        
   	   MqttClient sampleClient = mqttservicemgr.mqttconnect();
       String data =  mqttservicemgr.mqttdatasubscribe(sampleClient);
       visualizeSensorData(data);

    }
    
    public  static void  DisplayIoTConfig(JTable table)
    {
    //	 JFrame f = new JFrame();
     //    f.setSize(300, 200);
         f.add(new JScrollPane(table));
         f.setVisible(true);
         
    	
    }
    
   
    
    public static  void visualizeSensorData(String data)
    {
    	
    	 System.out.println("visualizeSensorData : " +data);
    	 int firstIndex = data.indexOf("temperature :");
    	 int secondIndex = data.indexOf("SmokeDetected");
		 
    	 System.out.println("First index is " +firstIndex);
		 System.out.println("secondIndex is " +secondIndex);
		 JOptionPane.showMessageDialog(regFrame, "Temperature > 50 Anomaly detected ",
                 "WARNING", JOptionPane.WARNING_MESSAGE);

		 boolean tempanomalyfound = false;
		// int anomalyevtcount =0;
		 


		 if(firstIndex >0 )
     	{
    	     String tempcheck = data.substring(firstIndex+13,firstIndex+15 );
   	    	 System.out.println("Temperature found " +tempcheck);

    	    	 if(Integer.valueOf(tempcheck)> 40)
    	    	 {
    	    		 System.out.println(tempcheck);
    	    		 
    	    		 
    	    		 tempanomalyfound= true;
    	    		 anomalyevtcount++;
    		    	 System.out.println("AnomalyEvent Count  " +anomalyevtcount);

    	    		// if(anomalyevtcount == )
    	    		 //JOptionPane.showMessageDialog(regFrame, “Temperatue anomaly detected”);
    	    		 JOptionPane.showMessageDialog(regFrame, "Temperature > 50 Anomaly detected ",
                             "WARNING", JOptionPane.WARNING_MESSAGE);

    			 }  	
    	    	 
    	}  	
		 
		 if(secondIndex >0 )
		 {
			 String SmokeCheck = data.substring(secondIndex+13,firstIndex+17 );
	    	 System.out.println(SmokeCheck);
	    	 System.out.println("Smoke Event Detected " +SmokeCheck);


	    	 anomalyevtcount++;
	    	 
	    	 System.out.println("AnomalyEvent Count  " +anomalyevtcount);

 
			 
		 }
	  if(anomalyevtcount == 2)
	  {
		  JOptionPane.showMessageDialog(regFrame, "Cascading anomaly detected- Temperature and Smoke Events ",
                  "WARNING", JOptionPane.WARNING_MESSAGE);
		 //Reset Anomaly Evt Count 
		  anomalyevtcount=0;
  
	  }
    
    	  sensordata.append("\n");
          sensordata.append(data);
        // scrolls the text area to the end of data
          sensordata.setCaretPosition(sensordata.getDocument().getLength());
    	
    }
    public static  void visualizeRegistrationData(String data)
    {
    	
    	 System.out.println("visualizeRegistrationData : " +data);
    	// displaymgr.createAnomalyDataManagerUX();
    //	 displaymgr.renderSensorData(40,40,data);
    	 //displaymg
    	// displaymgr.setLiveStreamData(data, 10, 20);
    	 
    //	 displaySensorData
     	
    	 Deviceregistrations.append("\n");
    	 Deviceregistrations.setText(data);
    	 Deviceregistrations.append(data);
    	 Font font = new Font("Verdana", Font.BOLD, 16);
    	 Deviceregistrations.setFont(font);
    	 Deviceregistrations.setForeground(Color.MAGENTA);    	 
    	// Deviceregistrations.setSelectedTextColor("ORANGE");
        // scrolls the text area to the end of data
    	 Deviceregistrations.setCaretPosition(Deviceregistrations.getDocument().getLength());
    	
    }
    public static  void visualizeSensorStreamingData(String data)
    {
    	
    	 System.out.println("visualizeSensorStreamingData : " +data);
     	
         sensordata.append("\n");
         sensordata.append(data);
         sensordata.setText(data);

    	 Font font = new Font("Verdana", Font.BOLD, 16);
    	 sensordata.setFont(font);
    	 sensordata.setForeground(Color.MAGENTA);    	 
    	// Deviceregistrations.setSelectedTextColor("ORANGE");
        // scrolls the text area to the end of data
    	 sensordata.setCaretPosition(sensordata.getDocument().getLength());
    	
    }
  }
   
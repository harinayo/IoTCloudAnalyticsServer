package com.iot.CloudAnalytics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NewDataModelInterface {

    static JComboBox cGateway;

	
	public  void createDataModelUX()
	{
		
	    final JFrame adpativeModelFrame = new JFrame("IoT Cloud Analytics Server  ");

	    JPanel p = new JPanel();
       
        JPanel p1 = new JPanel();
     
        final JTextArea AttibuteArea  = new JTextArea(5, 20);  
        Font font = new Font("Verdana", Font.BOLD, 14);
        AttibuteArea.setFont(font);
    //    AttibuteArea.setForeground(Color.BLUE);

       
        final JTextArea CommandsArea = new JTextArea(5, 20); 
        CommandsArea.setFont(font);
        
        JTextArea SerialNo = new JTextArea(3, 10); 

 	 JLabel lattributedata = new JLabel("Model Attributes ");
 	 Dimension ssize = lattributedata.getPreferredSize();
     Font labelfont = new Font("Verdana", Font.BOLD, 16);

 	lattributedata.setFont(labelfont);
   //    AttibuteArea.setForeground(Color.BLUE);
 	lattributedata.setForeground(Color.BLUE);

 	//lattributedata.setBackground(Color.BLUE);
 	lattributedata.setOpaque(true);


 	 lattributedata.setBounds(100, 1000, ssize.width, ssize.height);
 	 p.add(lattributedata);
 	 p.add(AttibuteArea);


	 JLabel lServiceCommandsdata = new JLabel("Service Commands");
	 lServiceCommandsdata.setForeground(Color.BLUE);
	 lServiceCommandsdata.setOpaque(true);
	 lServiceCommandsdata.setFont(labelfont);

	 
    
	 

	 JLabel GatewayType = new JLabel("Gateway Type  ");
 //	 Dimension ssize = lattributedata.getPreferredSize();
	 GatewayType.setFont(labelfont);

	 GatewayType.setForeground(Color.BLUE);


	// lServiceCommandsdata.setBounds(500, 1000, ssize.width, ssize.height);
 	 p.add(lServiceCommandsdata);
 	 p.add(CommandsArea);
 	 p.add(GatewayType);
 	 
 	 
 	  //  Create  Gateway Selection Menu
	    // array of string containing cities
	       String s1[] = { "HVAC", "Patient Monitoring","Agriculture", "Industrial Monitoring" };
	    
	 
	        // create checkbox
	       cGateway = new JComboBox(s1);

	       cGateway.setSelectedIndex(3);
	 
	        // add ItemListener
	    //   cGateway.addItemListener(gateway);
	 
	        // set the checkbox as editable
	       cGateway.setEditable(true);
	       
	       p.add(cGateway);
 	 //p.add(lserilaNo);
 	 
 	 
 	 
 	 //p.add(SerialNo);
 	 
 	 
 	 adpativeModelFrame.add(p);
 	 
 	 
 	JButton bModel=new JButton("Push Data Model ");
 	bModel.setSize(50, 50);

 	//bModel.setBounds(50,100,95,30);
 	adpativeModelFrame.add(bModel);
 	adpativeModelFrame.setSize(200,200);
 	
 	
 	bModel.addActionListener(
            new ActionListener(){
				@Override

                public void actionPerformed(ActionEvent e) {
					String integdata = null;
					String Attrdata = AttibuteArea.getText();
			    	 System.out.println("Attribute data  : " + Attrdata);
			    	 String integdataAttr ="{Attributes:";
			    	 integdataAttr = integdataAttr.concat(Attrdata);
			    	 
			    	 
			    	 String CommandInfo ="}\n{Commands:";
					String commanddata = CommandsArea.getText();
					CommandInfo = CommandInfo.concat(commanddata);
					
					String commandclose = CommandInfo.concat("}");

			    	 System.out.println("Command  data  : " +commandclose);

			    	 integdata= integdataAttr.concat(commandclose);
					 System.out.println("Integrated  data  : " +integdata);
                    JOptionPane.showMessageDialog(adpativeModelFrame,
                            "Pushing data model to the gateway"
                            );
                    pushDataModel(integdata);
                }

			
            });

 	adpativeModelFrame.setSize(500,500);  
        
 	adpativeModelFrame.setVisible(true);  
 	adpativeModelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // set flow layout for the frame  
  	adpativeModelFrame.getContentPane().setLayout(new FlowLayout());  
		
	}
	
	public void pushDataModel(String data )
	{
		String topic = "/IoTGateway/DynamicModel";
		IoTGatewayConfigWizard cloudModelpush = new IoTGatewayConfigWizard();
		cloudModelpush.pushDataModeltoGateway(topic,data);
	}
	
}

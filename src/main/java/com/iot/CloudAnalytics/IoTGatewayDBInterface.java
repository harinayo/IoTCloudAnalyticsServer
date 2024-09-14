/**
 * 
 */
package com.iot.CloudAnalytics;

import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Hariprasad Anumala 
 *
 */    
public class IoTGatewayDBInterface {

	/**
	 * 
	 */
	
	public IoTGatewayDBInterface() {
		// TODO Auto-generated constructor stub
		
		
		   }

	
   
	public static void IoTGatewayAddConfigData()
	{
		// String    IOT_DB_URL = "jdbc:mysql://localhost:3306/db";
		 
		 String IOT_DB_URL = "jdbc:mysql://localhost/mysql?autoReconnect=true&useSSL=false";
		// String IOT_DB_URL= "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";

		// load and register JDBC driver for MySQL
		 try {
			//Class.forName("com.mysql.jdbc.Driver");
			 Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		
		 try(Connection conn = DriverManager.getConnection(IOT_DB_URL, "root", "iotdb@123");
		         Statement stmt = conn.createStatement();
		      ) {		      
		        
		          
		          String sqld = "CREATE TABLE IoTGatewayConfig " +
		                   "id INTEGER NOT NULL,"+
		                   " deploymenttype VARCHAR(255), " + 
		                   " sensortype VARCHAR(255), " + 
		                   " attribute INTEGER, " + 
		                   " MinThreshold INTEGER," +
		                   " MaxThreshold INTEGER"+
		                   " PRIMARY KEY ( id ))"; 
		          
		         /* String sqlB = "CREATE TABLE REGISTRATION " +
		                   "(id INTEGER not NULL, " +
		                   " first VARCHAR(255), " + 
		                   " last VARCHAR(255), " + 
		                   " age INTEGER, " + 
		                   " PRIMARY KEY ( id ))"; */
		          String sqla = "CREATE TABLE IF NOT EXISTS IoTGatewayConfig "
		                   +"("
		            + " ID serial,"
		            + " deploymenttype varchar(100) NOT NULL,"
		            + " sensortype varchar(255) NOT NULL,"
		            + " attribute varchar(255) NOT NULL,"
		            + " MinThreshold real NOT NULL,"
		            + " MaxThreshold real NOT NULL,"
		           // + " CREATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,"
		            + " PRIMARY KEY (ID)"
		            + ")";

		         stmt.executeUpdate(sqla);
		         System.out.println("Created table in given database...");   	
		         String sql1 = "INSERT INTO IoTGatewayConfig VALUES ('100','SmartHome', 'Room Temperature', 'temperatureval',5,40)";
		       //  stmt.executeUpdate(sql1);
		         String sql2 = "INSERT INTO IoTGatewayConfig VALUES ('101','SmartHome', 'GasLeakSensor', 'CarbonMonoxide',10,50)";
		       //  stmt.executeUpdate(sql2);
		         String sql3 = "INSERT INTO IoTGatewayConfig VALUES ('222','HumanbodyMonitoring', 'Temperature', 'temperatureval', 36.1,37.2)";
		         stmt.executeUpdate(sql3);
		         String sql4 = "INSERT INTO IoTGatewayConfig VALUES ('333','CattleMonitoring', 'Temperature', 'temperatureval',37,39)";
		         stmt.executeUpdate(sql4);
		         System.out.println("Inserted records into the table...");   
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
	}
	
	public static void IoTGatewayReadConfigData()
	{
		
		 String IOT_DB_URL = "jdbc:mysql://localhost/mysql?autoReconnect=true&useSSL=false";
			// String IOT_DB_URL= "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";

			// load and register JDBC driver for MySQL
			 try {
				//Class.forName("com.mysql.jdbc.Driver");
				 Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		 try(Connection conn = DriverManager.getConnection(IOT_DB_URL, "root", "iotdb@123");
		         Statement stmt = conn.createStatement();
		      ) {		      
		        
		      //   Statement stmt = conn.createStatement();
		     
		        //	 Connection con = null;
		             PreparedStatement p = null;
		             ResultSet rs = null;
		            // SQL command data stored in String datatype
		            String sql = "select * from IoTGatewayConfig";
		            p = conn.prepareStatement(sql);
		            rs = p.executeQuery();
		 
		          //  + " attribute varchar(255) NOT NULL,"
		          //  + " MinThreshold integer NOT NULL,"
		          //  + " MaxThreshold integer NOT NULL,"
		            System.out.println("id\t\tdeptype\t\tsensortype\t\tattribute\t\tminval\t\tmaxval");
		 
		            // Condiion check
		            while (rs.next()) {
		 
		                int id = rs.getInt("id");
		                String deptype = rs.getString("deploymenttype");
		            //    if(deptype.equals("SmartHome"))
		                {
		                	
		                String sensortype = rs.getString("sensortype");
		                String attribute = rs.getString("attribute");
		                int minval = rs.getInt("MinThreshold");
		                int maxval = rs.getInt("MaxThreshold");

		                System.out.println(id + "\t\t" + deptype
		                                   + "\t" + sensortype  +"\t" + attribute + "\t\t" +minval +"\t\t" +maxval  );
		                }
		                }
		        }
		 
		        // Catch block to handle exception
		        catch (SQLException e) {
		 
		            // Print exception pop-up on scrreen
		            System.out.println(e);
		        }		          
		 }


		
	
	public static GatewayConfigParams IoTGatewayReadData(String deploymentType)
	{
		 String IOT_DB_URL = "jdbc:mysql://localhost/mysql?autoReconnect=true&useSSL=false";
		 
		 GatewayConfigParams gwdata = new GatewayConfigParams();
			// String IOT_DB_URL= "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";

			// load and register JDBC driver for MySQL
			 try {
				//Class.forName("com.mysql.jdbc.Driver");
				 Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		 try(Connection conn = DriverManager.getConnection(IOT_DB_URL, "root", "iotdb@123");
		         Statement stmt = conn.createStatement();
		      ) {		      
		        
		      //   Statement stmt = conn.createStatement();
		     
		        //	 Connection con = null;
		             PreparedStatement p = null;
		             ResultSet rs = null;
		            // SQL command data stored in String datatype
		            String sql = "select * from IoTGatewayConfig";
		            p = conn.prepareStatement(sql);
		            rs = p.executeQuery();
		 
		            System.out.println("id\t\tdeptype\t\tsensortype\t\tattribute\t\tminval\t\tmaxval");
		 
		            // Condiion check
		            while (rs.next()) {
		 
		                int id = rs.getInt("id");
		                String deptype = rs.getString("deploymenttype");
		               if(deptype.equals(deploymentType))
		                {
		                String sensortype = rs.getString("sensortype");
		                gwdata.GatewayType = deptype;
		                
		                String attribute = rs.getString("attribute");
		                gwdata.attribute = attribute;
		                int minval = rs.getInt("MinThreshold");
		                gwdata.minval = minval;
		                int maxval = rs.getInt("MaxThreshold"); 
		                gwdata.maxval = maxval;

		                System.out.println(id + "\t\t" + deptype
		                                   + "\t" + sensortype  +"\t" + attribute + "\t\t" +minval +"\t\t" +maxval  );
		                }
		                }
		        }
		 
		        // Catch block to handle exception
		        catch (SQLException e) {
		 
		            // Print exception pop-up on scrreen
		            System.out.println(e);
		        }	
		 
		 
		 	 return gwdata;
		
		
	}
		  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        System.out.println("Creating IoTGateway Configuration...");   
	//	createIoTGatewayUX();

      //  IoTGatewayAddConfigData();
       IoTGatewayReadConfigData();

	}

}

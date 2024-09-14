package com.iot.CloudAnalytics;

public class GatewayConfigParams {

   // String sensortype = rs.getString("sensortype");
   // String attribute = rs.getString("attribute");
   // int minval = rs.getInt("MinThreshold");
   // int maxval = rs.getInt("MaxThreshold");
	String GatewayType;
	String sensortype ;
	String attribute;
	float  minval;
	float maxval;
	
	public void  printGatewayConfigParams(GatewayConfigParams  gwData)
	
	{
           
    	System.out.println(" Gateway Type is : " + gwData.GatewayType);
    	System.out.println(" Gateway Type is : " + gwData.sensortype);


		
	}
}

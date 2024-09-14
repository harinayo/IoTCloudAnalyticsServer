<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>  
<head>  
<title>dropdown menu using select tab</title>  
</head>  
<script>  
function favTutorial() {  
var mylist = document.getElementById("myList");  
document.getElementById("GatewayType").value = mylist.options[mylist.selectedIndex].text;  
}  

function readIoTGatewayConfig (a){ 
 //   return {
        result:com.iot.gateway.IoTGatewayDBInterface.
   //     }; 
}

</script>  
  
<body>  
<form>  
<b> Select IoT Gateway configuration Model  </b>  
<select id = "myList" onchange = "favTutorial()" >  
<option> ---Choose Gateway Type --- </option>  
<option> SmartHome </option>  
<option> HealthMonitoring </option>  
<option>Agriculture </option>  
<option> IndustrialIoT </option>  
</select>  

</form>  
</body>  
</html>  
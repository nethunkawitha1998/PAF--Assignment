<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Managment_System</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/hospital.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>HOSPITAL MANAGEMENT</h1>
				<form id="formItem" name="formItem" method="post" action="Hospital.jsp">  
					Hospital Code: 
					<input id="hospitalCode" name="hospitalCode" type="text" class="form-control form-control-sm">  
					<br> Hospital Name:  
					<input id="hospitalName" name="hospitalName" type="text" class="form-control form-control-sm">  
					<br> Hospital Email:   
					<input id="hospitalEmail" name="hospitalEmail" type="text" class="form-control form-control-sm">  
					<br> Hospital Description:  
					<input id="hospitalDesc" name="hospitalDesc" type="text" class="form-control form-control-sm">
					<br> Hospital District:  
					<input id="hospitalDistrict" name="hospitalDistrict" type="text" class="form-control form-control-sm">
					<br> Hospital Phone No:   
					<input id="hospitalTel" name="hospitalTel" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divItemsGrid">
					<%
						Hospital hosObj = new Hospital();
						out.print(hosObj.readHospital());
					%>
				</div>
				
				 
			</div>
		</div>
</div>

</body>
</html>
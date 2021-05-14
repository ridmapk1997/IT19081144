<%@ page import="com.customer_management.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Users.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">


				<h1>User Management</h1>
				<form method='post' action='Users.jsp' id='formUser' name='formUser'>
					User NIC: <input id='userNic' name='userNic' type='text' class='form-control col-md-12'><br> 
					User name: <input id='userName' name='userName' type='text' class='form-control col-md-12'><br> 
					Date of birth: <input id='dateofbirth' name='dateofbirth' type='text' class='form-control col-md-12'><br> 
					User description: <input id='userDesc' name='userDesc' type='text' class='form-control col-md-12'><br> 
					<input id='btnSave' name='btnSave' type='button' value='Save' class='btn btn-primary'> 
					<input type='hidden' id='hidUserIDSave' name='hidUserIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				<br>
				<div id="divItemsGrid">
				<%
					User userObjRead = new User();
						out.print(userObjRead.readUsers());
				%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>
<%@page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
	%>
	<div>
		<p>
			<b>Reports </b>
		</p>
	</div>
	<div>
		<form method="get" action="LateAndDefaulterList">
			<label>TOP 25 FO and ND </label><select class="form-control"
				name="category">
				<option value="FO">Field Officer</option>
				<option value="ND">Nizam Dost</option>
			</select> <input type="date" name="from" id="from"> <input type="date"
				name="to" id="to"><input type="submit" name="click"
				id="click" value="Generate TOP Report">
		</form>
	</div>
	<div>
		<form method="get" action="LateAndDefaulterList">
			<label> Late/Defaulter Report </label> <select class="form-control"
				name="categ">
				<option value="DO">District Officer</option>
				<option value="FO">Field Officer</option>
			</select> <input type="submit" name="click" id="click"
				value="Generate Late and Defaulter Report">
		</form>
	</div>
	<div>
		<form method="get" action="LateAndDefaulterList">
			<label> Loan Application Status </label> <select class="form-control"
				name="category">
				<option value="DO">District Officer</option>
				<option value="FO">Field Officer</option>
			</select><input type="date" name="from" id="from"> <input type="date"
				name="to" id="to"><input type="submit" name="click"
				id="click" value="Generate Loan Apps Report">
		</form>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label>Sales and Defaulters Report </label><select
				class="form-control" name="category">
				<option value="DO">District Officer</option>
				<option value="FO">Field Officer</option>
			</select><input type="date" name="from" id="from"> <input type="date"
				name="to" id="to"> <input type="submit" name="click"
				id="click" value="Generate defaulter Report">
		</form>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label>FO Sales Report (in Progress)</label> <input type="date"
				name="from" id="from"> <input type="date" name="to" id="to">
			<input type="submit" name="click" id="click"
				value="Generate FO Sales Report">
		</form>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label> New Installations </label> <input type="date" name="from"
				id="from"> <input type="date" name="to" id="to"> <input
				type="submit" name="click" id="click" value="Generate Report">
		</form>
	</div>
	<div>
		<p>
			<b>Data </b>
		</p>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label> DO/FO/ND Sales Data </label> <select class="form-control"
				name="category">
				<option value="DO">District Officer</option>
				<option value="FO">Field Officer</option>
				<option value="ND">Nizam Dost</option>
			</select> <input type="date" name="from" id="from"> <input type="date"
				name="to" id="to"> <input type="submit" name="click"
				id="click" value="Generate Sales Report">
		</form>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label> DO/FO/ND Recovery Data</label> <select class="form-control"
				name="category">
				<option value="DO">District Officer</option>
				<option value="FO">Field Officer</option>
				<option value="ND">Nizam Dost</option>
			</select> <input type="date" name="from" id="from"> <input type="date"
				name="to" id="to"> <input type="submit" name="click"
				id="click" value="Generate Recovery Report">
		</form>
	</div>
	<div>
		<form method="get" action="SalesReport">
			<label> Customer Rating </label> <input type="submit" name="click"
				id="click" value="Generate Customer Rating Report">
		</form>
	</div>
	<div>
		<form method="get" action="LateAndDefaulterList">
			<label> Late/Defaulter Customers </label> <select
				class="form-control" name="categ">
				<option value="customer">Customers</option>
			</select> <input type="submit" name="click" id="click"
				value="Generate Late and Defaulter Report">
		</form>
	</div>
	<%
		}
	%>
</body>
</html>
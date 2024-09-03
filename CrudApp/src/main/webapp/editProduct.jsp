<%@page import="com.seclore.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Product p = (Product) request.getAttribute("product");
%>
<form action="CrudProductServlet">
	<input type="hidden" name="action" value="update" />
	<input type="hidden" name="id" value="<%=p.getId()%>"/>
	Enter product name: <input type="text" name="name" value="<%=p.getName() %>" />
	<br/>
	Enter product price: <input type="text" name="price" value="<%=Double.toString(p.getPrice()) %>" />
	<br/>
	Enter product quantity: <input type="text" name="quantity" value="<%=Integer.toString(p.getQuantity()) %>" />
	<br/>
	<button type="submit">Update</button>
</form>
</body>
</html>
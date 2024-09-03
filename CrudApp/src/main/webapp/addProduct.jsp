<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/CrudApp">HOME</a>
<%
	Integer productId = (Integer) request.getAttribute("productId");
	if (productId!=null){
%>
<h3>Product added with id <%=productId %></h3>
<%
	}
%>
<form action="CrudProductServlet">
	<input type="hidden" name="action" value="add" />
	Enter product name: <input type="text" name="name"/>
	<br/>
	Enter product price: <input type="number" name="price"/>
	<br/>
	Enter product quantity: <input type="number" name="quantity"/>
	<br/>
	<button type="submit">Add</button>
</form>
</body>
</html>
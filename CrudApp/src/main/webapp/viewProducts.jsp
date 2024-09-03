<%@page import="com.seclore.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button>
<a href="CrudProductServlet?action=sort&type=lowToHigh">
Sort By: Low to High
</a>
</button>
<button>
<a href="CrudProductServlet?action=sort&type=highToLow">
Sort By: High to Low
</a>
</button>

<%
	List<Product> list = (List<Product>) request.getAttribute("listOfProducts");
	for (Product product : list){
%>
	<div>
		Id: <%=product.getId() %><br/>
		Name: <%=product.getName() %><br/>
		Price: <%=product.getPrice() %><br/>
		Quantity: <%=product.getQuantity() %> <br/>
		<a href="CrudProductServlet?action=edit&id=<%=product.getId()%>">Edit Product</a>
		
		<a href="CrudProductServlet?action=delete&id=<%=product.getId()%>">Delete Product</a>
		
	</div>
<%
	}
%>
</body>
</html>
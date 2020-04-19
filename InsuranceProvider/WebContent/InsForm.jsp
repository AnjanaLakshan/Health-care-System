<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("nic") != null)
{
 session.setAttribute("nic", request.getParameter("nic"));
 session.setAttribute("name", request.getParameter("name"));
 session.setAttribute("income", request.getParameter("income"));
 session.setAttribute("provider", request.getParameter("provider"));
 session.setAttribute("level", request.getParameter("level"));
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insurance Management</title>
</head>
<body>
<h1>Insurance Management</h1>
<form method="post" action="items.jsp">
 NIC: <input name="nic" type="text" ><br>
 Name: <input name="name" type="text" ><br>
 Monthly Income: <input name="income" type="text" ><br>
 Insurance Provider: <input name="provider" type="text" ><br>
 <input name="btnSubmit" type="submit" value="Save">
</form>
<br>
<table border="1">
 <tr>
 <th>NIC</th>
<th>Name</th>
<th>Monthly Income</th>
<th>Provider</th>
<th>User Level</th>
<th>Update</th>
<th>Remove</th>
 </tr>
 <tr>
 <td><%out.print(session.getAttribute("nic")); %></td>
 <td><%out.print(session.getAttribute("name")); %></td>
 <td><%out.print(session.getAttribute("income")); %></td>
 <td><%out.print(session.getAttribute("provider")); %></td>
 <td><%out.print(session.getAttribute("level")); %></td>
 <td><input name="btnUpdate" type="button" value="Update"></td>
 <td><input name="btnRemove" type="button" value="Remove"></td>
 </tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String message = (String)request.getSession().getAttribute("message"); 
	if (message == null) {
		message = "";
	}
%>
<%=message %>
<form action="WriteData" method="post">
		<input type="text" name="data" /> <input type="submit" />
	</form>
</body>
</html>
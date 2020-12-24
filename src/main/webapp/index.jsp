<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
   <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<form action="test-servlet" method="get">
   <textarea name="cmd"></textarea>
   <input type="submit" value="Submit">
   <p>${execResult}</p>
</form>

</body>
</html>
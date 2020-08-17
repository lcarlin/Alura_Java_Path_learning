<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Telinha de loguinho</title>
</head>
<body>

    <form action="${linkEntradaServlet}" method="post">

		Loginho : <input type="text" name="login"/>
		Password: <input type="password" name="senha"/>
		<input type="hidden" name="acao" value="Login" />
		<input type="submit" />
	</form>
</body>
</html>
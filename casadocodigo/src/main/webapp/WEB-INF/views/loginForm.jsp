<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm.jsp - LIvros: Java, And-roid, Ruby , PHP e muito +
	em A Casa de O Cóodigo</title>

<c:url value="../resources/css" var="cssPath" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="../resources/js/bootstrap.min.js"></script>
</head>
<style type = "text/css">
body {
padding: 60px 0px ;
}
</style>
<body>
	
	<div class="container">
	<h1>Loguinho da casa de O Código</h1>
		<form:form servletRelativeAction="/login" method="POST" >
			<div class="form-group">
				<label>E-Mail </label>
				<input name="username" type="text" Class="form-control" />				
			</div>
			<div class="form-group">
				<label>Senha</label>
				<input type = "password" name="password" Class="form-control"  />
			</div>
			<button type="submit" class="btn btn-primary">Efetuar Login</button>
		</form:form>
	</div>
</body>
</html>
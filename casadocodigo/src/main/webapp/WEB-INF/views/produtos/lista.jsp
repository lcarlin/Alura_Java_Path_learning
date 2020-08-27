<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lista.jsp - LIvros: Java, And-roid, Ruby , PHP e muito +
	em A Casa de O Cóodigo</title>
</head>
<body>
<h1>Lista de produtos já cadastrados.</h1>
<div>${sucesso }</div>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Paginas</td>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>
					<a href ="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }"> ${produto.titulo} </a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

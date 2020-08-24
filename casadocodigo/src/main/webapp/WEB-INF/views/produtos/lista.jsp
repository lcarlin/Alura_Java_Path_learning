<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Livros de programação de novas tecnologias - A Casa De O
	Cóodigos</title>
</head>
<body>
<h1>Lista de Os Produtos</h1>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
		</tr>
		<c:forEach items ="${produtos }" var = "produtos">
		<tr>
			<td>${produtos.titulo}</td>
			<td>${produtos.descricao}</td>
			<td>${produtos.paginas}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>


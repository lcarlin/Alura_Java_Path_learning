<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,br.com.alura.gerenciador.modelo.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Standard TagLib.</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:if test= "${not empty empresa}">
            Empresa ${ empresa } cadastrada com sucesso!
        </c:if>


		Lista de Empreixas: <br/>
		<ul>
			<c:forEach items="${empresas}" var="empresa">				
				<li>
					${empresa.nome} -  <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy "/>
					<a href="/gerenciador/mostraEmpresa?id=${empresa.id}">ALterar/Editar Empresa</a>
					<a href="/gerenciador/removeEmpresa?id=${empresa.id}">Remover Empresa</a>
					
				</li> 				
			</c:forEach>
		</ul>		 
</body>
</html>
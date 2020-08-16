package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm implements Acao {
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======================================");
		System.out.println("Register a New Enterprise v2!");
				
		// response.sendRedirect("entrada?acao=ListaEmpresas");
		return "forward:formNovaEmpresa.jsp" ;
	}
}

package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;

public class RemoveEmpresa implements Acao {
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======================================");
		System.out.println("Removendo as empresas");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId); 
		System.out.println(id);
		
		Banco banco = new Banco() ;
		banco.removeEmpresa(id) ;
		
		// response.sendRedirect("entrada?acao=ListaEmpresas");
		return "redirect:entrada?acao=ListaEmpresas" ;

	}
}

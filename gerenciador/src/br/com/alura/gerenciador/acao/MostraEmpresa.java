package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class MostraEmpresa implements Acao{
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======================================");
		System.out.println("Mostra as empresas");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId); 
		
		Banco banco = new Banco () ;
		Empresa empresa = banco.buscaEmpresaPelaID (id); 
		
		System.out.println(empresa.getNome());
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		request.setAttribute("empresa", empresa);
		// RequestDispatcher rd = request.getRequestDispatcher("/formAlteraEmpresa.jsp" ); 
		// rd.forward(request, response);
		// response.sendRedirect("entrada?acao=ListaEmpresas");
		return "forward:/formAlteraEmpresa.jsp" ;
		

	}
}

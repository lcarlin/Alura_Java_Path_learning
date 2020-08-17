package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet implementation class UnicaEntradaServlet
 */
//@WebServlet(urlPatterns="/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String paramAcao = request.getParameter("acao") ;
//		
//		HttpSession sessao = request.getSession() ; 
//		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null  ) ; 
//		boolean ehUmaAcaoProtegida = !(  paramAcao.equals("Login") || paramAcao.equals("LoginForm")) ; 
//		
//		if ( ehUmaAcaoProtegida && usuarioNaoEstaLogado ) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			return ;
//		}
		
		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao ; 
		
		String nome ;   
		try {
			Class classe = Class.forName(nomeDaClasse) ; // carrega apenas a classe necessárias.
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response); 
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new ServletException(e) ;
		} 

		String[] tipoEndereco = nome.split(":");
		if ( tipoEndereco[0].equals("forward") ) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEndereco[1]);
		}
	}

}

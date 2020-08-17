package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login") ;
		String senha = request.getParameter("senha") ;
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println("Loguinho: " + login );
		
		Banco banco = new Banco ()  ; 
		Usuario  usuario = banco.existeUsuario(login, senha) ;  
		
		if ( usuario != null ) {
			System.out.println("O usuario existe");
			HttpSession sessao = request.getSession() ; 
			sessao.setAttribute("usuarioLogado",usuario  );
			return "redirect:entrada?acao=ListaEmpresas" ; 
		} else {
			System.out.println("O usuario " +login + " Non ecsiste");
			return "redirect:entrada?acao=LoginForm" ;
		}		
	}

}
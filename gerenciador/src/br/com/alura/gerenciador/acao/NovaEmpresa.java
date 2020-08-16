package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class NovaEmpresa {
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======================================");
		System.out.println("Register a New Enterprise!");
		String nomeEmpresa = request.getParameter("nome");
		String paraDataEmpresa = request.getParameter("data");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAbertura = null ;
		try {
			dataAbertura = sdf.parse(paraDataEmpresa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new ServletException(e) ; 
		}
		
		Empresa empresa = new Empresa() ;
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco (); 
		banco.adiciona(empresa) ; 

		System.out.println("Enterprise -> " + nomeEmpresa);
 
		request.setAttribute("empresa", empresa.getNome());
		
		// response.sendRedirect("entrada?acao=ListaEmpresas");
		return "redirect:entrada?acao=ListaEmpresas" ;
	}
}

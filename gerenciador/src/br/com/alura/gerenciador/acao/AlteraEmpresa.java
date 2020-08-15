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

public class AlteraEmpresa {
	public void executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======================================");
		
		String nomeEmpresa = request.getParameter("nome");
		String paraDataEmpresa = request.getParameter("data");
		String paramId = request.getParameter("id");		
		Integer id = Integer.valueOf(paramId) ;
		
		System.out.println("Alterando  as empresas " + id );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAbertura = null ;
		try {
			dataAbertura = sdf.parse(paraDataEmpresa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new ServletException(e) ; 
		}

		
		Banco banco = new Banco () ;
		Empresa empresa = banco.buscaEmpresaPelaID(id);
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		response.sendRedirect("entrada?acao=ListaEmpresas");

	}
}

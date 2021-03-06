package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

/**
 * Servlet implementation class AlteraEmpresasServlet
 */
// @WebServlet("/alteraEmpresa")
public class AlteraEmpresasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("======================================");
		System.out.println("Modify an existing Enterprise!");
		
		String nomeEmpresa = request.getParameter("nome");
		String paraDataEmpresa = request.getParameter("data");
		String paramId = request.getParameter("id");		
		Integer id = Integer.valueOf(paramId) ;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAbertura = null ;
		try {
			dataAbertura = sdf.parse(paraDataEmpresa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new ServletException(e) ; 
		}
		System.out.println(id);
		
		Banco banco = new Banco () ;
		Empresa empresa = banco.buscaEmpresaPelaID(id);
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		response.sendRedirect("listaEmpresas");
	}

}

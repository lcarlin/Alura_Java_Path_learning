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
 * Servlet implementation class NovaEmpreaServlet
 */
// @WebServlet("/novaEmpresa")
public class NovaEmpreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
//		PrintWriter out = response.getWriter();
//		out.println("<html><body> Empresa ->"+ nomeEmpresa+ " <- cadastrada com o sucessos </body></html>");
		System.out.println("Enterprise -> " + nomeEmpresa);
		// chamr o JSP de visualização 
		
//		RequestDispatcher rd = request.getRequestDispatcher("/novaEmpresaCriada.jsp");
		request.setAttribute("empresa", empresa.getNome());
		response.sendRedirect("listaEmpresas");
		
//		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas");	
//		request.setAttribute("empresa", empresa.getNome());
//		rd.forward(request, response);
	}

}

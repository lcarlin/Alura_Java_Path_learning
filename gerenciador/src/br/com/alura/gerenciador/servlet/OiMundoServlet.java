package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/oi")
public class OiMundoServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4628190983452256105L;
	   public OiMundoServlet() {
	        System.out.println("Criando Oi Mundo Servlet");
	    }


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		// TODO Auto-generated method stub
		// super.service(req, resp);
		PrintWriter out = resp.getWriter();
		out.println("<html>") ; 
		out.println("<body>") ;
		out.println("Hello World - Congrats - You have writen your first Servlet !!! !! !");
		out.println("</body>") ;
		out.println("</html>") ;
		System.out.println("Apalpa minha naba caso possa!");
	}

}


package br.com.bytebank.test ;

import br.com.bytebank.banco.modelo.Conexao;

public class TesteConexao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Conexao conexao = new Conexao () ) {
			conexao.leDados(); 
		} catch (IllegalStateException ex) {
			System.out.println("Erro de connecction");
		}
//		Conexao con = null ; 
//		try {
//			con = new Conexao() ;
//			con.leDados();
//		} catch (IllegalStateException ex) {
//			System.out.println("Erro de connecction");
//		} finally {
//			con.fecha();
//		}
//
	}

}

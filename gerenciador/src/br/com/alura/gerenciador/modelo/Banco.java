package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	private static List<Empresa> lista = new ArrayList<> () ;
	private static List<Usuario> listaUsuarios = new ArrayList<> () ;
	private static Integer chaveSequencial = 1 ; 
	
	static {
		Empresa empresa = new Empresa () ;
		empresa.setId(chaveSequencial++);
		empresa.setNome("P!r0c4#93");
		
		Empresa empresa2 = new Empresa () ;
		empresa2.setId(chaveSequencial++);
		empresa2.setNome("bucet@#69");
		
		Empresa empresa3 = new Empresa () ;
		empresa3.setId(chaveSequencial++);
		empresa3.setNome("C4r410#93");
		
		Empresa empresa4 = new Empresa () ;
		empresa4.setId(chaveSequencial++);
		empresa4.setNome("Sh4w4sk4#51");
		
		lista.add(empresa);
		lista.add(empresa2);
		lista.add(empresa3);
		lista.add(empresa4);
		
		Usuario u1 = new  Usuario () ;
		u1.setLogin("nico");
		u1.setSenha("senha") ;
		
		Usuario u2 = new  Usuario () ;
		u2.setLogin("ana");
		u2.setSenha("senha") ;
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		
		
	}

	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.lista.add(empresa) ; 
		// TODO Auto-generated method stub		
	}
	
	public List<Empresa> getEmpresas(){
		return Banco.lista ; 
	}

	public void removeEmpresa(Integer id) {		
		Iterator<Empresa> it = lista.iterator() ; 		
		while ( it.hasNext() ) {
			Empresa emp = it.next() ;
			if (emp.getId() == id ) {
				it.remove() ; 
			}	
		}
	}

	public Empresa buscaEmpresaPelaID(Integer id) {
		// TODO Auto-generated method stub
		for (Empresa empresa : lista) {
			if ( empresa.getId() == id ) {
				return empresa ;				
			}			
		}
		return null;
	}

	public Usuario existeUsuario(String login, String senha) {
		// TODO Auto-generated method stub
		for (Usuario usuario : listaUsuarios) {
			if (usuario.ehIgual(login, senha)) {
				return usuario; 
			}
		}
		return null;
	}
}


public class TesteReferencias {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gerente g1 = new Gerente ()  ; 
		g1.setNome("Novo Gerente fodão");
		// String nome = g1.getNome()  ; 
		g1.setSalario(5000);
		
		
//		Funcionario f1 = new Funcionario() ; 
//		f1.setSalario( 2000 );
//		// System.out.println(nome );
		
		EditorVideo e1 = new EditorVideo() ; 
		e1.setSalario( 2500 );
		
		Designer d1 = new Designer() ; 
		d1.setSalario(2000);
		
		ControleBonificacao controle = new ControleBonificacao() ; 
		controle.registra(g1 );
//		controle.registra(f1);
		controle.registra(e1);
		controle.registra(d1);
		
		
		System.out.println(controle.getSoma() );

	}

}

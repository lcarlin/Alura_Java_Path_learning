
public class TestaContaSemCliente {
	public static void main(String[] args) {
		Conta contaDaMarcela = new Conta() ;
		
		System.out.println(contaDaMarcela.saldo);
		
		contaDaMarcela.titular = new Cliente() ;
		System.out.println(contaDaMarcela.titular);
		contaDaMarcela.titular.nome = "MArcela" ; 
		System.out.println(contaDaMarcela.titular.nome);
			
	}

}

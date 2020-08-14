
public class TestaGetESet {
	public static void main(String[] args) {
		Conta conta = new Conta();
		//conta.numero= 1337 ; 
		conta.setNumero(1337);
		
		System.out.println(conta.getNumero() );
		Cliente paulo = new Cliente()  ;
		
		paulo.setNome("Paulo da sILVEIRA");
		paulo.setCpf("222.222.222-22");
		paulo.setProfissao("programador");
		
		
		conta.setTitular(paulo); 
		
		System.out.println(conta.getTitular().getNome() );
		
		conta.getTitular().setProfissao("teste de profissao" );
		System.out.println(conta.getTitular().getProfissao()  );
		
	}

}

// o grerente é um , gherda do funcinários
public class Gerente extends Funcionario implements Autenticavel {
	
	private AutenticacaoUtil autenticador ;
	
	public Gerente () {
		this.autenticador = new AutenticacaoUtil() ; 
	}


	public double getBonificacao() {
		System.out.println("Bonificacao Gerente");
//		return super.getBonificacao() + ( super.getSalario() * 1.0) ;
		return super.getSalario() * 1.0 ; 
	}


	@Override
	public void setSenha(int senha) {
		// TODO Auto-generated method stub
		// this.senha = senha ;
		this.autenticador.setSenha(senha);
		
	}

	@Override
	public boolean autentica(int senha) {
		return this.autenticador.autentica(senha) ; 
		// TODO Auto-generated method stub
//		if (this.senha == senha ) {
//			return true ;
//		} else {
//			return false;
//		}
	}

	
}

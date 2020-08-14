
public class Administrador extends Funcionario implements Autenticavel {
	//private int senha ;
//	private int senha ;
	private AutenticacaoUtil autenticador ;
	public Administrador () {
		this.autenticador = new AutenticacaoUtil() ; 
	}
	
	
	@Override
	public double getBonificacao() {
		// TODO Auto-generated method stub
		return 50;
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

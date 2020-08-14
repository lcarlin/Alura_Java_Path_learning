
public class Cliente implements Autenticavel {
	
//	private int senha ;
	private AutenticacaoUtil autenticador ;
	
	public Cliente () {
		this.autenticador = new AutenticacaoUtil() ; 
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

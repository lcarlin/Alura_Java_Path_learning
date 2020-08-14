
public class AutenticacaoUtil {
	private int senha ; 
	
	
	public void setSenha(int senha) {
		// TODO Auto-generated method stub
		this.senha = senha ;	
	}

	
	public boolean autentica(int senha) {
		// TODO Auto-generated method stub
		if (this.senha == senha ) {
			return true ;
		} else {
			return false;
		}
	}

}

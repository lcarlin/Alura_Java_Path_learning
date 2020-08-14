
public class CriaConta {
	public static void main(String[] args) {
		Conta primeiraConta = new Conta() ;
		primeiraConta.saldo = 200 ; 
		System.out.println(primeiraConta.saldo);
		
		primeiraConta.saldo += 100 ;
		System.out.println(primeiraConta.saldo);
		
		Conta segundaConta = new Conta() ;
		segundaConta.saldo = 50 ; 
		
		System.out.println("Saldo da primeira conta " + primeiraConta.saldo);
		System.out.println("Saldo em segunda conta  " + segundaConta.saldo);
		
		System.out.println(primeiraConta.agencia);
		System.out.println(primeiraConta.numero);
		System.out.println(primeiraConta.titular);
		
		
		segundaConta.agencia = 146  ; 
		System.out.println(segundaConta.agencia );
		
		if ( primeiraConta == segundaConta ) {
			System.out.println("Mesma COnta");
			
		} else {
			System.out.println("Outra Conta");
		}
		
		
	}
}


public class Conta {
	private double saldo ;
	private int agencia; 
	private int numero ;
	private Cliente titular ;
	private static int total  ;
	
	public Conta (int agencia, int numero ) {
		Conta.total ++ ; 
		// System.out.println("O total de contas � " + Conta.total );
		this.numero = numero ;
		this.agencia = agencia ;
		
		// System.out.println ("Estou a criar uma nova conta " + this.numero) ; 
		
	}
	
	//========================================= 
	void deposita (double valor ) {
		this.saldo += valor  ; 	
	}
	
	//=========================================
	public boolean saca ( double valor ) {
		if ( this.saldo >= valor ) {
			this.saldo -= valor ; 
			return true ; 
		} else {
			return false ; 
		}
	}
	
	//================================
	public boolean transfere ( double valor, Conta destino ) {
		if (this.saldo >= valor ) {
			this.saldo -= valor ; 
			destino.deposita(valor);
			return true ;
		} else {
			return false ; 
		}		
	}
	
	//================================
	public double getSaldo() {
		return this.saldo ; 
	}
	
	//================================
	public int getNumero() {
		return this.numero ; 
	}
	
	//================================
	public void setNumero (int novoNumero ){
		if (novoNumero <= 0 ) {
			System.out.println("N�o pode valor menor igual zeroi ");
			return ;
		}		
		this.numero = novoNumero ; 
	}
	
	//================================
	public void setAgencia (int novaAgencia ) {
		if (novaAgencia <= 0 ) {
			System.out.println("N�o pode valor menor igual zeroi ");
			return ;
		}
		this.agencia = novaAgencia ; 
	}
	
	//================================
	public int getAgencia () {
		return this.agencia ;
	}
	
	//================================
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	//================================
	public Cliente getTitular() {
		return titular;
	}
	
	//================================
	public static int getTotal () {
		return Conta.total ; 
	}
	
	//================================
	//================================
	//================================
	
}
public class TestaMetodo {
	public static void main(String[] args) {
		Conta contaPaulo = new Conta() ;
		contaPaulo.saldo = 100 ; 
		contaPaulo.deposita(50);
		System.out.println(contaPaulo.saldo);
		boolean conseguiuRetirar = contaPaulo.saca(20);
		
		System.out.println(contaPaulo.saldo);
		System.out.println(conseguiuRetirar);
		
		Conta contaMarcela = new Conta()  ; 
		contaMarcela.deposita(1000);
		boolean sucessoTransferencia = contaMarcela.transfere(3000, contaPaulo )  ;
		if ( sucessoTransferencia  ) {
			System.out.println("Transferencia com sucesso");
			System.out.println(contaMarcela.saldo);
			System.out.println(contaPaulo.saldo);
		} else {
			System.out.println("Faltou dinheoro");
		}		
	}
}

public class TesteWhile {
	public static void main(String[] args) {
		int contador = 0 ;
		while (contador <= 10) {
			System.out.println("O valor do contador é :-> " + contador );
			// contador ++ ;
			contador += 1 ; 
		}
		System.out.println("E o valor final foi :-> " + contador );
	}
}

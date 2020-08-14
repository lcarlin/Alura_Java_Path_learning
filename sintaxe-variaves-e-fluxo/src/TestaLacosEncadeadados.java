
public class TestaLacosEncadeadados {
	public static void main(String[] args) {
		for(int multiplicador = 1; multiplicador < 11 ; multiplicador++ ) {
			for (int contador = 0; contador < 11; contador++ ) {
				System.out.print((multiplicador * contador));
				System.out.print(" ") ; 
			}
			System.out.println() ; 
		}
	}

}


public class TestaEscopoVariavel {
	public static void main(String[] args) {
		System.out.println("Testando os condicionais ");
		int idade = 22;
		int quantidadePessoas = 1 ;
		// boolean acompanhado = false  ;
		// boolean acompanhado = quantidadePessoas > 2 ;]
		// declara��es de variaves devem vir antes dos {}, sen�o elas existir�o somente nesse escopo 
		boolean acompanhado ;
		
		if (quantidadePessoas > 2) {
			// ainda n�o existe
			// boolean acompanhado = true ;
			// existe dentro dos {} somente
			acompanhado = true ;
		} else {
			// ainda n�o existe 
			// boolean acompanhado = false ;
			// existe dentro dos {} somente
			acompanhado = false ;
		}
		
		System.out.println("Valor de acompanhado = " + acompanhado); 
		
		if (idade >= 18 && acompanhado  ) {
			System.out.println("Voc� temmais de 18 ");
			System.out.println("Be Welcome");
		} else {
				System.out.println("too young to die");
		}		
	}
}


public class TestaCondicional2 {
	public static void main(String[] args) {
		System.out.println("Testando os condicionais ");
		int idade = 22;
		int quantidadePessoas = 1 ;
		// boolean acompanhado = false  ;
		boolean acompanhado = quantidadePessoas > 2 ;
		
		System.out.println("Valor de acompanhado = " + acompanhado); 
		
		if (idade >= 18 && acompanhado  ) {
			System.out.println("Voc� temmais de 18 ");
			System.out.println("Be Welcome");
		} else {
				System.out.println("too young to die");
		}		
	}
}

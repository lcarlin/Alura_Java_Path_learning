
public class TestaCondicional {
	public static void main(String[] args) {
		System.out.println("Testando os condicionais ");
		int idade = 2;
		int quantidadePessoas = 3 ; 
		if (idade >= 18) {
			System.out.println("Voc� temmais de 18 ");
			System.out.println("Be Welcome");
		}
		else {
			if (quantidadePessoas >= 2) {
				System.out.println("Voc� n�o tem mais de 18, "
						+ "mas pode entrar pois esta acompanhado");
			} else {
				System.out.println("too young to die");
				
			}
		}
				
	}
}

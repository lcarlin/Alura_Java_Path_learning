package br.com.bytebank.test;

public class TesteArrayDePrimitivos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] idades = new int [5] ; /// inicializa com valores padrões = 0   

		//		idades[0] = 29 ;
//		idades[1] = 39 ;
//		idades[2] = 49 ;
//		idades[3] = 59 ;
//		idades[4] = 69 ;
//		int idade4 = idades[4] ; 
//		System.out.println(idade4);
//		System.out.println(idades.length);

		for (int i = 0; i < idades.length ; i++) {
			idades[i] = ((i * i) * i ) + 2 ;  
		}

		for (int i = 0; i < idades.length ; i++) {
			System.out.println(idades[i]);
		}


	}

}

package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> palavras = new ArrayList<String>();
		palavras.add("Zumbido");
		palavras.add("Editoras");
		palavras.add("Casa de O codigo");
		palavras.add("Coelestium");
		palavras.add("Alura em linha ");
		Comparator<String> comparador = new ComparadorPorTamanho();

		// Collections.sort(palavras, comparador);
		// palavras.sort(comparador);
		palavras.sort(( s1,  s2) -> {
			// TODO Auto-generated method stub
			if (s1.length() < s2.length()) {
				return -1;
			}
			if (s1.length() > s2.length()) {
				return 1;
			}
			return 0;
		});
		
		palavras.sort(( s1,  s2) -> Integer.compare(s1.length(), s2.length()));
		
		palavras.sort(Comparator.comparing(s -> s.length() ));
		palavras.sort(Comparator.comparing(String::length));
		
		Function<String, Integer> funcao = s -> s.length();
		Comparator<String> comparadorA = Comparator.comparing(funcao);
		palavras.sort(comparadorA);
						
		System.out.println("======================================");
		System.out.println(palavras);
		for (String p : palavras) {
			System.out.println(p);

		}
		System.out.println("======================================");
		// Consumer<String> consumidor = new ImprimeNaLinha() ;
		// better Way ?
//		Consumer<String> consumidor = new Consumer<String>() {
//
//			@Override
//			public void accept(String t) {
//				// TODO Auto-generated method stub
//				System.out.println(t);
//			}
//		};
		// palavras.forEach(consumidor);
		// Better Way ?
//		palavras.forEach( new Consumer<String>() {
//
//			@Override
//			public void accept(String t) {
//				// TODO Auto-generated method stub
//				System.out.println(t);
//			}
//		});
		// \Better Way 3
//		palavras.forEach( (String t) -> {
//				// TODO Auto-generated method stub
//				System.out.println(t);
//			}
//		);
		// Better Way 4
		palavras.forEach(s -> System.out.println(s));
		
		Consumer<String> impressor = s -> System.out.println(s);
		palavras.forEach(impressor);
	}
}

class ImprimeNaLinha implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);

	}

}

class ComparadorPorTamanho implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		// TODO Auto-generated method stub
		if (s1.length() < s2.length()) {
			return -1;
		}
		if (s1.length() > s2.length()) {
			return 1;
		}
		return 0;
	}

}
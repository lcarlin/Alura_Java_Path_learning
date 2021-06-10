package java8;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Datas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate hoje = LocalDate.now(); 
		System.out.println(hoje);
		LocalDate futuro = LocalDate.of(2025,Month.APRIL, 30);
		int anos = futuro.getYear() - hoje.getYear(); 
		System.out.println(anos);
		
		Period periodo = Period.between(hoje, futuro);
		System.out.println(periodo.getDays());
				

	}

}

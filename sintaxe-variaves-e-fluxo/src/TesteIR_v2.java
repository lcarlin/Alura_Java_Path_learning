
public class TesteIR_v2 {
    public static void main(String[] args) {

        double salario = 300.0;

        //ifs aqui
        if (salario>=1900.00 && salario <=2800.00) {
        	System.out.println("A sua aliquota é de 7,5% ");
        	System.out.println("O valor a ser deduzido é de R$ 142 ");
        } else {
        	if (salario>= 2800.01 && salario <= 3751.00) {
            	System.out.println("A sua aliquota é de 15% ");
            	System.out.println("O valor a ser deduzido é de R$ 350 ");
            } else {
            	if (salario>= 3751.01 && salario <= 4664.00) {
                	System.out.println("A sua aliquota é de 22.5% ");
                	System.out.println("O valor a ser deduzido é de R$ 696 ");
                } else {
                	if (salario>= 4664.01 ) {
                		System.out.println("Se fudeu ");
                		System.out.println("A sua aliquota é de 27.5% ");
                		System.out.println("O valor a ser deduzido é de R$ 869,36 ");
                	} else {
                		System.out.println("Isentão??? " + salario );
                	}
                }
            	
            }
        }
    }
	
}

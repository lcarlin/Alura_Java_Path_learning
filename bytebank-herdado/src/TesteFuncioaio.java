
public class TesteFuncioaio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gerente nico = new Gerente()   ;
		nico.setNome("Seppat");
		nico.setCpf("222.222.222-22");
		nico.setSalario(2600.0);
		
		System.out.println(nico.getNome());
		System.out.println(nico.getBonificacao());
		
		nico.setSalario(nico.getSalario() +nico.getBonificacao()  );
		System.out.println(nico.getSalario() );
	}

}

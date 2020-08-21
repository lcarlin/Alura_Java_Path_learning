package br.com.alura.jpa.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return nome + " - " + id ;
    }
    
    @Deprecated
    public Categoria() {
		// TODO Auto-generated constructor stub
	}
    
    public Categoria(String nome ) {
		// TODO Auto-generated constructor stub
    	super();
    	this.nome = nome; 
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private String nome ;

}

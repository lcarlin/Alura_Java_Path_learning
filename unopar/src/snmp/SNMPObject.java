/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPObject.java
 * Pacote snmp
 * Função classe base abstrata para todas as classes de dados do SNMP
 * Data  13/10/2002
 * Modificação 
 ****************************************************************/

package snmp;



public abstract class SNMPObject
{
	
	/** 
	*   deve retornar um Objeto Java(TM) apropriado para representa o valor/dado
	*  contido no Objeto SNMP
	*/
	
	public abstract Object getValue();
	
	
	
	/** 
	*	Must set the value of the SNMP object when supplied with an appropriate
	* 	Java object containing an appropriate value.
	*  
	* deve configurar o valor do Objeto SNMP quando fornecido com um objeto java
	* apropriado contendo um valor também apropriado
	*
	*/
	
	public abstract void setValue(Object o)
		throws SNMPBadValueException;
	
	
	
	/** 
	*   deve retornar uma representaçào legivel do valor armazenado
	*/
		
	public abstract String toString();
	
	
	
	/** 
	*   Deve retornar o byte de codificação BER (tipo, tamanho, valor) do objeto SNMP
	*/
		
	protected abstract byte[] getBEREncoding();
	
	
}
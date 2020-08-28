/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPObject.java
 * Pacote snmp
 * Fun��o classe base abstrata para todas as classes de dados do SNMP
 * Data  13/10/2002
 * Modifica��o 
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
	* apropriado contendo um valor tamb�m apropriado
	*
	*/
	
	public abstract void setValue(Object o)
		throws SNMPBadValueException;
	
	
	
	/** 
	*   deve retornar uma representa��o legivel do valor armazenado
	*/
		
	public abstract String toString();
	
	
	
	/** 
	*   Deve retornar o byte de codifica��o BER (tipo, tamanho, valor) do objeto SNMP
	*/
		
	protected abstract byte[] getBEREncoding();
	
	
}
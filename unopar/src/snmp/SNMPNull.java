/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPNull.java
 * Pacote snmp
 * Fun��o Objetos que representam o tipo de dados nulo do SNMP
 * Data  13/10/2002
 * Modifica��o 
 ****************************************************************/


package snmp;



public class SNMPNull extends SNMPObject
{
	
	protected byte tag = SNMPBERCodec.SNMPNULL;
	
	
	/**
	*   Retorna uma referencia nula do Java(TM)
	*/
	
	public Object getValue()
	{
		return null;
	}
	
	
	
	/**
	*    sempre lan�a a excess�o SNMPBadValueException 
	*/
	
	public void setValue(Object o)
		throws SNMPBadValueException
	{
		throw new SNMPBadValueException(" Null: attempt to set value ");
	}
	
	
	
	/**
	*  retorna a codifica��o BER para um objeto nulo, 2 bytes, tag e tamanho 0
	*/
	
	protected byte[] getBEREncoding()
	{
		byte[] encoding = new byte[2];
		
		
		// configura o byte de tag
		encoding[0] = SNMPBERCodec.SNMPNULL;
			
		// len = 0 		
		encoding[1] = 0;
		
		// sem  V!
		
		return encoding;
	}
	
	
	
	/**
	*   retorna o String nulo
	*/
	
	public String toString()
	{
		return new String("Null");
	}
	
}
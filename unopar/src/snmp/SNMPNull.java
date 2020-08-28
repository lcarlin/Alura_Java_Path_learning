/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPNull.java
 * Pacote snmp
 * Função Objetos que representam o tipo de dados nulo do SNMP
 * Data  13/10/2002
 * Modificação 
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
	*    sempre lança a excessão SNMPBadValueException 
	*/
	
	public void setValue(Object o)
		throws SNMPBadValueException
	{
		throw new SNMPBadValueException(" Null: attempt to set value ");
	}
	
	
	
	/**
	*  retorna a codificaçào BER para um objeto nulo, 2 bytes, tag e tamanho 0
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
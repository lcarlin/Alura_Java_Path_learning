/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPTimeTicks.java
 * Pacote snmp
 * Fun��o tipo de dado do SNMO usado para representar valore de tempo
 *        apenas uma extens�o do SNMPInteger
 * Data  13/10/2002
 * Modifica��o 
 ****************************************************************/


package snmp;


public class SNMPTimeTicks extends SNMPInteger
{
	
	public SNMPTimeTicks()
	{
		this(0);	// inicializa valor em zero
	}
	
	
	public SNMPTimeTicks(long value)
	{
		super(value);
		
		tag = SNMPBERCodec.SNMPTIMETICKS;
	}
	
	
	protected SNMPTimeTicks(byte[] enc)
		throws SNMPBadValueException
	{
		super(enc);
		
		tag = SNMPBERCodec.SNMPTIMETICKS;
	}
	
}
/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPTimeTicks.java
 * Pacote snmp
 * Função tipo de dado do SNMO usado para representar valore de tempo
 *        apenas uma extensão do SNMPInteger
 * Data  13/10/2002
 * Modificação 
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
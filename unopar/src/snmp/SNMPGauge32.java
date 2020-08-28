/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPGauge32.java
 * Pacote snmp
 * Função define uma medida de 32-bits, cujos valores fixam no maximo se inicializado
 * com um valor grande 
 * Data  12/10/2002
 * Modificação 
 ****************************************************************/


package snmp;




import java.math.*;



public class SNMPGauge32 extends SNMPInteger
{
	// valor maximo 2^32 - 1 (hack w/ 4*107...)
	private static BigInteger maxValue = new BigInteger("4294967295");
	
	/** inicializa valor em 0.
	*/
	
	public SNMPGauge32()
	{
		this(0);	// inicializa valor em 0.
	}
	
	
	public SNMPGauge32(long newValue)
	{
		tag = SNMPBERCodec.SNMPGAUGE32;
		
		value = new BigInteger(new Long(newValue).toString());
		
		// fixa  se valor > maxValue
		value = value.min(maxValue);
	}
	
	
		
	
	/**
	*  Usado para inicializar à partir de uma condificaçào BER, gealmente recebida
	*  em resposta à um dispositivo SNMP respondendo à um SNMPGetRequest
	* @throws SNMPBadValueException indica uma codificaçào BER inválida 
	*    fornecida, o que não deve ocorrer em operações normais
	*
	*/
	
	protected SNMPGauge32(byte[] enc)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPGAUGE32;
		
		extractValueFromBEREncoding(enc);
		
		// fixa  se valor > maxValue
		value = value.min(maxValue);
	}
	
	
	
	
	/**
	* usado para configurar o valor com uma instancia do java.lang.Integer ou
	* java.lang.BigInteger.. Os valores do Objeto SNMPGauge32 constuidos são 
	* fornecidos pelo valor mod 2^32
	*@throws SNMPBadValueException indica que foi fornecido um objeto de tipo incorreto
	*
	*/
	
	public void setValue(Object newValue)
		throws SNMPBadValueException
	{
		
		// fixa quando o valor atinge o maxValue
		if (newValue instanceof BigInteger)
		{
			value = (BigInteger)newValue;
			value = value.min(maxValue);
		}
		else if (newValue instanceof Integer)
		{
			value = value = new BigInteger(newValue.toString());
			value = value.min(maxValue);
		}
		else if (newValue instanceof String)
		{
			value = value = new BigInteger((String)newValue);
			value = value.min(maxValue);
		}
		else
			throw new SNMPBadValueException(" Gauge32: bad object supplied to set value ");
	}
	
	
}
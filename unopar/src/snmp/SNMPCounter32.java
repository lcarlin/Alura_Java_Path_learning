/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPCounter32.java
 * Pacote snmp
 * Função define um contador de 32-bits, cujos valores quebram se inicializado
 *     com um valor grande. 
 * Data  12/10/2002
 * Modificação 
 ****************************************************************/



package snmp;



import java.math.*;




public class SNMPCounter32 extends SNMPInteger
{
	// o valor máximo é 2^32 - 1
	private static BigInteger maxValue = new BigInteger("4294967295");
	
	
	
	/** Inicializa o valor em  0.
	*/
	
	public SNMPCounter32()
	{
		this(0);	// Inicializa o valor em  0
	}
	
	
	public SNMPCounter32(long newValue)
	{
		tag = SNMPBERCodec.SNMPCOUNTER32;
		
		value = new BigInteger(new Long(newValue).toString());
		
		// quebra se valor > maxValue
		value = value.mod(maxValue);
	}
	
	
	
	/**
	*  Usado para inicializar à partir de uma condificaçào BER, gealmente recebida
	*  em resposta à um dispositivo SNMP respondendo à um SNMPGetRequest
	* @throws SNMPBadValueException indica uma codificaçào BER inválida 
	*    fornecida, o que não deve ocorrer em operações normais
	*
	*/
	
	protected SNMPCounter32(byte[] enc)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPCOUNTER32;
		
		extractValueFromBEREncoding(enc);
		
		// quebra se valor > maxValue
		value = value.mod(maxValue);
	}
	
	
	
	
	
	/**
	* usado para configurar o valor com uma instancia do java.lang.Integer ou
	* java.lang.BigInteger.. Os valores do Objeto SNMPCounter32 constuidos são 
	* fornecidos pelo valor mod 2^32
	*@throws SNMPBadValueException indica que foi fornecido um objeto de tipo incorreto
	*
	*/
	
	public void setValue(Object newValue)
		throws SNMPBadValueException
	{
		if (newValue instanceof BigInteger)
		{
			value = (BigInteger)newValue;
			value = value.mod(maxValue);	// quebra o valor quando exceder 2^32
		}
		else if (newValue instanceof Integer)
		{
			value = new BigInteger(newValue.toString());
			value = value.mod(maxValue);	// quebra o valor quando exceder2^32
		}
		else if (newValue instanceof String)
		{
			value = value = new BigInteger((String)newValue);
			value = value.mod(maxValue);	// quebra o valor quando exceder 2^32
		}
		else
			throw new SNMPBadValueException(" Counter32: bad object supplied to set value ");
	}
	
	
	
	
}
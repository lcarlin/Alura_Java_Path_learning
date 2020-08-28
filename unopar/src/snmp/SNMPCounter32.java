/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPCounter32.java
 * Pacote snmp
 * Fun��o define um contador de 32-bits, cujos valores quebram se inicializado
 *     com um valor grande. 
 * Data  12/10/2002
 * Modifica��o 
 ****************************************************************/



package snmp;



import java.math.*;




public class SNMPCounter32 extends SNMPInteger
{
	// o valor m�ximo � 2^32 - 1
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
	*  Usado para inicializar � partir de uma condifica��o BER, gealmente recebida
	*  em resposta � um dispositivo SNMP respondendo � um SNMPGetRequest
	* @throws SNMPBadValueException indica uma codifica��o BER inv�lida 
	*    fornecida, o que n�o deve ocorrer em opera��es normais
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
	* java.lang.BigInteger.. Os valores do Objeto SNMPCounter32 constuidos s�o 
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
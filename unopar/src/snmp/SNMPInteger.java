/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInteger.java
 * Pacote snmp
 * Função define um inteiro de tamanho arbitrário, não há limites no tamanho, 
 *        devido ao uso da linguagem Java(TM)
 * Data  12/10/2002
 * Modificação 
 ****************************************************************/

package snmp;



import java.math.*;
import java.io.*;




public class SNMPInteger extends SNMPObject
{
	protected BigInteger value;
	protected byte tag = SNMPBERCodec.SNMPINTEGER;
	
	/** inicializa o valor em 0
	*/
	
	public SNMPInteger()
	{
		this(0);	// inicializa o valor em 0
	}
	
	
	public SNMPInteger(long value)
	{
		this.value = new BigInteger(new Long(value).toString());
	}
	
	
	
	
	
	/**
	* usado para inicializar à partir da condificação Ber. geralmente recebida em resposta
	*  à um dispositivo SNMP, respondendo à um SNMPGetrequest
	* @throws SNMPBadValueException indica que uma codificaçào BER inválida foi fornecida
	*  o que não deve ocorrer em operaçòes normais
	*
	*/
	
	protected SNMPInteger(byte[] enc)
		throws SNMPBadValueException
	{
		extractValueFromBEREncoding(enc);
	}	
	
	
	
	
	/*
	 * Retorna um objeto  BigInteger da linguagem JAVA(TM) com o valor corrernte
	*/
	
	public Object getValue()
	{
		return value;
	}
	
	
	
	
	
	/**
	*  usado para configurar o valor com uma isntancia do java.lang.Integer ou
	* java.lang.BigInteger.
	* @throws SNMPBadValueException indica que foi fornecido um tipo de objeto incorreto.
	*/
	
	public void setValue(Object newValue)
		throws SNMPBadValueException
	{
		if (newValue instanceof BigInteger)
			value = (BigInteger)newValue;
		else if (newValue instanceof Integer)
			value = new BigInteger(((Integer)newValue).toString());
		else if (newValue instanceof String)
			value = new BigInteger((String)newValue);
		else
			
			throw new SNMPBadValueException(" Integer: bad object supplied to set value ");
	}
	
	
	
	
	/** 
	 * retorna a codificaçào BER completa. (tipo, tamanho, valor) de uma subclasse SNMPInteger
	*/
	
	protected byte[] getBEREncoding()
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		
		// grava o conteudo
		// facilidades do JAVA(TM) 
		
		byte[] data = value.toByteArray();
		
		
		// calcula a codificaçào pelo tamanho do dado
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		
		
		// codifica a informaçào T, L, V
		outBytes.write(tag);
		outBytes.write(len, 0, len.length);
		outBytes.write(data, 0, data.length);
	
		return outBytes.toByteArray();
	}
	
	
	
	
	/**
	* Usado para extrair um valor à partir de uma codificaçào BER de um valor. 
	*  Chamado nos construtores para a sublcasse SNMPInteger
	*  @throws SNMPBadValueException indica uma codificaçào BER inválida, o que não deve ocorrer
	*   em operaçòes noemais
	*/
	
	public void extractValueFromBEREncoding(byte[] enc)
		throws SNMPBadValueException
	{
		try
		{
			value = new BigInteger(enc);
		}
		catch (NumberFormatException e)
		{
			throw new SNMPBadValueException(" Integer: bad BER encoding supplied to set value ");
		}
	}
	
	
	
	public String toString()
	{
		return value.toString();
		// return new String(value.toString());
	}
	
	
	
}
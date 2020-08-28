/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPObjectidentifier.java
 * Pacote snmp
 * Fun��o Classe que representa os identidficadores. objetos ASN.1
 *        S�o matrizes ilimtadas de numeros naturais, escritos como 
 *        strings separados por pontos
 * Data  10/10/2002
 * Modifica��o 
 *****************************************************************/


package snmp;

import java.util.*;
import java.io.*;



public class SNMPObjectIdentifier extends SNMPObject
{
	private int[] digits;	// array of integers
	
	protected byte tag = SNMPBERCodec.SNMPOBJECTIDENTIFIER;	
	
	/**
	*       Cria um novo objeto identificador vazio ( matriz tamanho 0)
	*/
	
	public SNMPObjectIdentifier()
	{	
		digits = new int[0];
	}
	
	
	
	
	/**
	*   Cria um novo objeto-identificado � partir do string  fornecido de decimais inteiros
	*   n�o negativos
	*  @throws SNMPBadValueException  indica que um string formatado incorretamento foi fornecido
	*/
	
	public SNMPObjectIdentifier(String digitString)
		throws SNMPBadValueException
	{
		convertDigitString(digitString);
	}
	
	
	
	
	/**
	*   cria um novo identificador de objetos � partir da matriz fornecida 
	*   de valores inteiros n�o negativos
	*   @throws SNMPBadValueException  indica valores negativos fornecidos
	*
	*/
	
	public SNMPObjectIdentifier(int[] digits)
		throws SNMPBadValueException
	{
		for (int i = 0; i < digits.length; i++)
		{
			if (digits[i] < 0)
				throw new SNMPBadValueException("Negative value supplied for SNMPObjectIdentifier.");
		}
				
		this.digits = digits;
	}
	
	
	
	
	/** 
	*   Utilizado para inicializar � partir de BER ( basic encoding rules), como 
	*   resposta de um dispositivo SNMP em resposta � um SNMPGetRequest
	*   @throws SNMPBadValueException indica uma codifica��o BER invalida. n�o 
	*   deve ocorrer em opera��es normais , p. ex. quando respostas v�lidas s�o 
	*   recebidas de dispositivos
	*
	*/
	
	protected SNMPObjectIdentifier(byte[] enc)
		throws SNMPBadValueException
	{
		extractFromBEREncoding(enc);
	}
	
	
	
	
	/** 	
	*   Retorna uma matriz de inteiros correspondente aos componentes identificados
	*
	*/
	
	public Object getValue()
	{
		return digits;
	}
	
	
	
	
	
	/** 
	*   usado para setar os valores de uma matriz de inteiros que contem os 
	*   componentes identificadores, ou de uma sring que cont�m uma sequencia de 
	*   valores n�o negativos separados por ponto
	*   @throws SNMPBadValueException  indica que um tipo incorreto de objeto 
	*  foi fornecido, ou elementos negativos na matriz, ou strings formatados
	*  incorretamente
	*
	*
	*/
	
	public void setValue(Object digits)
		throws SNMPBadValueException
	{
		if (digits instanceof int[])
		{
			for (int i = 0; i < ((int[])digits).length; i++)
			{
				if (((int[])digits)[i] < 0)
					throw new SNMPBadValueException("Negative value supplied for SNMPObjectIdentifier.");
			}
			
			this.digits = (int[])digits;
		}
		else if (digits instanceof String)
		{
			convertDigitString((String)digits);
		}
		else
			throw new SNMPBadValueException(" Object Identifier: bad object supplied to set value ");
	}
	
	
	
	
	/**
	*   retorna codifica��o BER para este identificador de objetos
	*/
	
	protected byte[] getBEREncoding()
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		byte type = SNMPBERCodec.SNMPOBJECTIDENTIFIER;
			
		// escreve o conteudo de matriz de elementos
		byte[] data = encodeArray();
		
		// calcula a codifica��o para o tamanho do dado
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		

		// codifica a informa��o T, L, V 
		outBytes.write(type);
		outBytes.write(len, 0, len.length);
		outBytes.write(data, 0, data.length);
		
		return outBytes.toByteArray();
	}
	
	
	
	private byte[] encodeArray()
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		int numElements = digits.length;
		
		// codifica os 2 primeiros digitos como um byte, usando a regra 40*x+y
		// setiuver apenas um elemento, apenas usar� 40*x, se n�o tiuver nenhum
		// n�o far� nada
		if (numElements >= 2)
		{
			outBytes.write((byte)(40*digits[0] + digits[1]));
		}
		else if (numElements ==1)
		{
			outBytes.write((byte)(40*digits[0]));
		}
		
		
		for (int i = 2; i < numElements; ++i)
		{
			byte[] nextBytes = encodeValue(digits[i]);
			outBytes.write(nextBytes, 0, nextBytes.length);
		}
		
		
		return outBytes.toByteArray();
	}
	
	
	
	private byte[] encodeValue(int v)
	{
		// verifica quantos bytes s�o necess�rios. cada valor utiliza apenas 7 bits
		// de cada byte, com funcionalidade de alta-ordem como uma marca��o continua
		int numBytes = 0;
		int temp = v;
		
		do
		{
			++numBytes;
			temp = (int)Math.floor(temp / 128);
		}
		while (temp > 0);
		
		
		byte[] enc = new byte[numBytes];
		// codifica o byte de menor-ordem sem setar o alto bit
		enc[numBytes-1] = (byte)(v % 128);
		v = (int)Math.floor(v / 128);
		
		// codifica outros bytes com High Bit Set
		for (int i = numBytes-2; i >= 0; --i)
		{
			enc[i] = (byte)((v % 128) + 128);
			v = (int)Math.floor(v / 128);
		}
		
		return enc;
	}
	
	
	
	private void convertDigitString(String digitString)
		throws SNMPBadValueException
	{
		try
		{
			StringTokenizer st = new StringTokenizer(digitString, " .");
			int size = 0;
			
			while (st.hasMoreTokens())
			{
				// determina quantos valores existem na string
				size++;
				st.nextToken();
			}
			
			int[] returnDigits = new int[size];
			
			st = new StringTokenizer(digitString, " .");
			
			for (int i = 0; i < size; i++)
			{
				returnDigits[i] = Integer.parseInt(st.nextToken());
				if (returnDigits[i] < 0)
					throw new SNMPBadValueException(" Object Identifier: bad string supplied to set value ");
			}
			
			digits = returnDigits;
			
		}
		catch (NumberFormatException e)
		{
			throw new SNMPBadValueException(" Object Identifier: bad string supplied for object identifier value ");
		}
		
		
	}
	
	
	
	
	private void extractFromBEREncoding(byte[] enc)
		throws SNMPBadValueException
	{
		
		// Mascaras devem ser inteiros
		int bitTest = 0x80;	
		int highBitMask = 0x7F;	
		
		
		// primeiramente, computa o numero de digitos
		// ser� o numero de bytes precedidos de 0
		int numInts = 0;
		for (int i = 0; i < enc.length; i++)
		{
			// Bit de alta-ordem n�o setado e contado
			if ((enc[i] & bitTest) == 0)		
				numInts++;
		}
		
		if (numInts > 0)
		{
			// cria uma nova matriz de inteiros para contar digitos
			// desde que o primeiro valor seja 40*x+y
			// precisa de uma entrada extra na mantriz para saber disto.
			digits = new int[numInts + 1];	
			
			int currentByte = -1;	// will be incremented to 0
			
			int value = 0;
			
			// le os valores at� chegar ao byte precedente 0
			do
			{
				currentByte++;
				value = value*128 + (enc[currentByte] & highBitMask);
			}
			while ((enc[currentByte] & bitTest) > 0);	// implica em um Alto-bit
			

			// manipula 40a + b
			digits[0] = (int)Math.floor(value / 40);
			digits[1] = value % 40;
			
			
			// le o resto
			for (int i = 2; i < numInts + 1; i++)
			{
				
				// le os valores at� chegar ao byte precedente 0
				value = 0;
				do
				{
					currentByte++;
					value = value*128 + (enc[currentByte] & highBitMask);
				}
				while ((enc[currentByte] & bitTest) > 0);
				
				digits[i] = value;
			}
			
		}
		else
		{
			
			// sem digitos, cria uuma matriz de digitos vazia
			digits = new int[0];
		}
		
	}
	
	
	
	
	/**
	*   testa 2 identificadores de objetos para verificar igualdade
	*/
	
	public boolean equals(SNMPObjectIdentifier other)
	{
		int[] otherDigits = (int[])(other.getValue());
		
		boolean areEqual = true;
		
		if (digits.length != otherDigits.length)
		{
			areEqual = false;
		}
		else
		{
			for (int i = 0; i < digits.length; i++)
			{
				if (digits[i] != otherDigits[i])
				{
					areEqual = false;
					break;
				}
			}
		}
		
		return areEqual;
			
	}
	
	
	
	/**
	*   retorna sequencia de valores decimais separados por ponto
	*/
	
	public String toString()
	{
		String valueString = new String();
		if (digits.length > 0)
		{
			valueString += digits[0];
			
			for (int i = 1; i < digits.length; ++i)
			{
				valueString += "." + digits[i];
			}
		}
		
			
		return valueString;
	}
	
	
	
	
}
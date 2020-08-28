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
 * Fun��o Classes para a manipula��o de octetos
 * Data  10/10/2002
 * Modifica��o 
 *****************************************************************/


package snmp;

import java.util.*;
import java.io.*;


/**
*   Classe representando um strnig gerl de octetos
*/

public class SNMPOctetString extends SNMPObject
{
	protected byte[] data;
	protected byte tag = SNMPBERCodec.SNMPOCTETSTRING;
		
	
	/**
	*   cria um string de octeto de tamanho maior que zero
	*/
	
	public SNMPOctetString()
	{
		data = new byte[0];
	}
	
	
	/**
	*   cria um string de octeto � partir de um byte fornecido pelo String
	*/
	
	public SNMPOctetString(String stringData)
	{
		this.data = stringData.getBytes();
	}
	
	
	
	
	/**
	*   cria um string de octeto � partir do byte fornecido pela matriz. a matriz
	*   pode ser : fornecida pelo usuario ou parte recuperada da codifica��o BER.
	*   a codifica��o BER dos dados de um string octeto � apenas bytes
	*/
	
	public SNMPOctetString(byte[] enc)
	{
		extractFromBEREncoding(enc);
	}
	
	
	
	
	/**
	*  retorna a matriz de bytes puros
	*/
	
	public Object getValue()
	{
		return data;
	}
	
	
	
	
	/** 
	*   Usado para configurar o valor � partir de uma matriz de bytes
	*   @throws SNMPBadValueException indica um tipod e objeto incorreto fornecido
	*
	*/
	
	public void setValue(Object data)
		throws SNMPBadValueException
	{
		if (data instanceof byte[])
			this.data = (byte[])data;
		else if (data instanceof String)
			this.data = ((String)data).getBytes();
		else
			throw new SNMPBadValueException(" Octet String: bad object supplied to set value ");
	}
	
	
	
	
	
	/** 
	*   Retorma a codifica��o BER para o string de octetos
	*/
	
	protected byte[] getBEREncoding()
	{
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		
		// calcula a codifica��o para o tamanho do dado
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		
		
		// codifica a informa��o T.L.V
		outBytes.write(tag);
		outBytes.write(len, 0, len.length);
		outBytes.write(data, 0, data.length);
	
		return outBytes.toByteArray();
	}
	
	
	
	
	protected void extractFromBEREncoding(byte[] enc)
	{
		data = new byte[enc.length];
		
		
		// copia o dado
		for (int i = 0; i < enc.length; i++)
		{
			data[i] = enc[i];
		}
	}
	
	
	
	
	
	/** 
	*   retorna o ustring construido � partir dos bytes puros. 
	*/
	
	public String toString()
	{
		String returnString;
		
		/*
		if ((data.length == 4) || (data.length == 6))
		{
			returnString = new String();
		
			int convert = data[0];
			if (convert < 0)
					convert += 256;
				returnString += convert;
					
			for (int i = 1; i < data.length; i++)
			{
				convert = data[i];
				if (convert < 0)
					convert += 256;
				returnString += "." + convert;
			}
		}
		else
			returnString = new String(data);
		*/
		
		/*
		byte[] converted = new byte[data.length];
		
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == 0)
				converted[i] = 0x20;	// espa�os
			else
				converted[i] = data[i];
		}
		
		returnString = new String(converted);
		*/
		
		returnString = new String(data);
		
		return returnString;
		
	}
	
	
	
	private String hexByte(byte b)
	{
		int pos = b;
		if (pos < 0)
			pos += 256;
		String returnString = new String();
		returnString += Integer.toHexString(pos/16);
		returnString += Integer.toHexString(pos%16);
		return returnString;
	}
	
	
	
	/** 
	*   retorna um string hexadecimal separado por espa�os, correspondente aos bytes puros
	*/
	
	public String toHexString()
	{
		String returnString = new String();
		
		
		for (int i = 0; i < data.length; i++)
		{
			returnString += hexByte(data[i]) + " ";
		}
		
		return returnString;
		
	}
	
	
	
}
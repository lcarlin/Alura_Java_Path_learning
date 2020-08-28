/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPObjectidentifier.java
 * Pacote snmp
 * Função Classes para a manipulaçào de octetos
 * Data  10/10/2002
 * Modificação 
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
	*   cria um string de octeto à partir de um byte fornecido pelo String
	*/
	
	public SNMPOctetString(String stringData)
	{
		this.data = stringData.getBytes();
	}
	
	
	
	
	/**
	*   cria um string de octeto à partir do byte fornecido pela matriz. a matriz
	*   pode ser : fornecida pelo usuario ou parte recuperada da codificaçào BER.
	*   a codificaçào BER dos dados de um string octeto é apenas bytes
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
	*   Usado para configurar o valor à partir de uma matriz de bytes
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
	*   Retorma a codificaçào BER para o string de octetos
	*/
	
	protected byte[] getBEREncoding()
	{
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		
		// calcula a codificaçào para o tamanho do dado
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		
		
		// codifica a informaçào T.L.V
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
	*   retorna o ustring construido à partir dos bytes puros. 
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
				converted[i] = 0x20;	// espaços
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
	*   retorna um string hexadecimal separado por espaços, correspondente aos bytes puros
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
/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPUnknownObject.java
 * Pacote snmp
 * Fun��o usado quando um Objeto desconhecido SNMP � encontrado. 
 *   apenas uma um byte da mariz para seu construtor. e usa a matriz de 
 *   bytes puros
 * Data  12/10/2002
 * Modifica��o 
 ****************************************************************/

package snmp;

import java.util.*;
import java.io.*;




public class SNMPUnknownObject extends SNMPObject
{
	private byte[] data;
	
	protected byte tag = SNMPBERCodec.SNMPUNKNOWNOBJECT;	
	
	/**
	* *   apenas uma um byte da mariz para seu construtor. e usa a matriz de 
    *   bytes puros
	*/
	public SNMPUnknownObject(byte[] enc)
	{
		data = enc;
	}
	
	
	
	
	/**
	*  Retorna uma matriz de byte contendo o byte puro fornecido
	*/
	public Object getValue()
	{
		return data;
	}
	
	
	
	
	/**
	*   pega a matriz  de bytes contedno o byte armazenado como o valor
	*/
	
	public void setValue(Object data)
		throws SNMPBadValueException
	{
		if (data instanceof byte[])
			this.data = (byte[])data;
		else
			throw new SNMPBadValueException(" Unknown Object: bad object supplied to set value ");
	}
	
	
	
	
	
	/**
	*   retorna a codifica��o BER para este objeto
	*/
	
	protected byte[] getBEREncoding()
	{
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		byte type = SNMPBERCodec.SNMPUNKNOWNOBJECT;
		
		
		// calcula a codifica��o para o tamanho do dado
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		
		// encode T,L,V info
		// codifica a informa��o T, L. V
		outBytes.write(type);
		outBytes.write(len, 0, len.length);
		outBytes.write(data, 0, data.length);
	
		return outBytes.toByteArray();
	}
	
	
	
	
	/**
	*  retorna o String criado � partir dos bytes deste objeto
	*/
	
	public String toString()
	{
		return new String(data);
	}
	
	
	
}
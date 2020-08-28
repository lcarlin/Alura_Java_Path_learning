/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPSAPAddress.java
 * Pacote snmp
 * Fun��o define classes para manuten��o fisica de endere�os de 6 bytes
 * Data  12/10/2002
 * Modifica��o 
 *****************************************************************/



package snmp;


import java.util.*;
import java.io.*;



public class SNMPNSAPAddress extends SNMPOctetString
{
	
	// tamanho limitado � 6 octetos
	
	
	/**
	*  inicializa endere�o para 0.0.0.0.0.0
	*/
	
	public SNMPNSAPAddress()
	{
		tag = SNMPBERCodec.SNMPNSAPADDRESS;
		
		// inicializa para  0.0.0.0.0.0
		data = new byte[6];
		for (int i = 0; i < 6; i++)
			data[i] = 0;
	}
	
	
	
	public SNMPNSAPAddress(String string)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPNSAPADDRESS;
		
		data = parseNSAPAddress(string);
	}
	
	
	
	
	/** 
	*   usado para inicializar � partir de uma codifica��o BER, como recebido em resposta
	*   �s um dispositivo SNMP respondento � um SNMPGetRequest ou de bytes de matriz fornecidos
	*   contendo o endere�o dos componentes
	*   @throws SNMPBadValueException  indica que foi fornecida uma matriz inv�lida
	*         deve ter tamanho 6
	*
	*/
	
	public SNMPNSAPAddress(byte[] enc)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPNSAPADDRESS;
		
		if (enc.length == 6)
		{
			data = enc;
		}
		else		// tamanho errado
		{
			throw new SNMPBadValueException(" NSAPAddress: bad BER encoding supplied to set value ");
		}
	}
	
	
	
	/** 
	*  usado para configurar o valor de uma matriz de byrtes contendo o endere�o  
	*   @throws SNMPBadValueException  indica que um objeto invalido foi fornecido
	*    ou uma matriz de tamanho incorreto
	*
	*/
	
	public void setValue(Object newAddress)
		throws SNMPBadValueException
	{
		if ((newAddress instanceof byte[]) && (((byte[])newAddress).length == 6))
			data = (byte[])newAddress;
		else if (newAddress instanceof String)
		{
			data = parseNSAPAddress((String)newAddress);
		}
		else
			throw new SNMPBadValueException(" NSAPAddress: bad length byte string supplied to set value ");
	}
	
	
	
	
	/** 
	*   retorna o endere�o formatado elegantemente ( essa � boa!!! )
	*/
	
	public String toString()
	{
		String returnString = new String();
		
		if (data.length > 0)
		{
			int convert = data[0];
			if (convert < 0)
					convert += 256;
				returnString += Integer.toHexString(convert);
					
			for (int i = 1; i < data.length; i++)
			{
				convert = data[i];
				if (convert < 0)
					convert += 256;
				returnString += "-" + Integer.toHexString(convert);
			}
		}
		
		return returnString;
	}
	
	
	
	private byte[] parseNSAPAddress(String addressString)
		throws SNMPBadValueException
	{
		try
		{
			
			// quebra em espa�os , pontos e tra�os
			StringTokenizer st = new StringTokenizer(addressString, " .-"); 
			int size = 0;
			
			while (st.hasMoreTokens())
			{
				
				// determina quantos valores est�o na string
				size++;
				st.nextToken();
			}
			
			if (size != 6)
			{
				throw new SNMPBadValueException(" NSAPAddress: wrong number of components supplied to set value ");
			}
			
			byte[] returnBytes = new byte[size];
			
			st = new StringTokenizer(addressString, " .-");
			
			for (int i = 0; i < size; i++)
			{
				int addressComponent = (Integer.parseInt(st.nextToken(), 16));
				if ((addressComponent < 0) || (addressComponent > 255))
					throw new SNMPBadValueException(" NSAPAddress: invalid component supplied to set value ");
				returnBytes[i] = (byte)addressComponent;
			}
			
			return returnBytes;
			
		}
		catch (NumberFormatException e)
		{
			throw new SNMPBadValueException(" NSAPAddress: invalid component supplied to set value ");
		}
		
	}
	
	
	
}
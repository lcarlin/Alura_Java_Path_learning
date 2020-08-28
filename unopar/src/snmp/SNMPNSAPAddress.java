/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPSAPAddress.java
 * Pacote snmp
 * Função define classes para manutençào fisica de endereços de 6 bytes
 * Data  12/10/2002
 * Modificação 
 *****************************************************************/



package snmp;


import java.util.*;
import java.io.*;



public class SNMPNSAPAddress extends SNMPOctetString
{
	
	// tamanho limitado à 6 octetos
	
	
	/**
	*  inicializa endereço para 0.0.0.0.0.0
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
	*   usado para inicializar à partir de uma codificaçào BER, como recebido em resposta
	*   às um dispositivo SNMP respondento à um SNMPGetRequest ou de bytes de matriz fornecidos
	*   contendo o endereço dos componentes
	*   @throws SNMPBadValueException  indica que foi fornecida uma matriz inválida
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
	*  usado para configurar o valor de uma matriz de byrtes contendo o endereço  
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
	*   retorna o endereço formatado elegantemente ( essa é boa!!! )
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
			
			// quebra em espaços , pontos e traços
			StringTokenizer st = new StringTokenizer(addressString, " .-"); 
			int size = 0;
			
			while (st.hasMoreTokens())
			{
				
				// determina quantos valores estão na string
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
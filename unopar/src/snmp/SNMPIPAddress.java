/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPIPAddress.java
 * Pacote snmp
 * Função Classe para guardar o endereço IP, especialmente nos casos
 *      de String de Octetos SNMP
 * Data  12/10/2002
 * Modificação 
 *****************************************************************/

package snmp;


import java.util.*;
import java.io.*;



public class SNMPIPAddress extends SNMPOctetString
{
	
	// Tamanho limitado à quatro Octetos
	
	
	/** 
	*	inicializa em 0.0.0.0
	*/
		
	public SNMPIPAddress()
	{
		// inicializa  0.0.0.0
		tag = SNMPBERCodec.SNMPIPADDRESS;
		data = new byte[4];
		for (int i = 0; i < 4; i++)
			data[i] = 0;
	}
	
	
	/** 
	*   usado pata inicializar à partir de um string com os pontos padrào do endereço IP
	*  @throws SNMPBadValueException  indica que foi fornecido um string inválido,
	*     mais de 4 componentes e valores que não estão entre 0 e 255
	* 
	*/
	
	public SNMPIPAddress(String string)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPIPADDRESS;
		this.data = parseIPAddress(string);
	}
	
	
	
	
	
	/** 
	*   usado para inicilizar à partir da codificaçào BER, como recebida em resposta à um 
	*   dispositivo SNMP respondendo à um SNMPGetRequest, ou de umamatriz de byres que contém
	*  o endereço dos componentes
	* 	@throws SNMPBadValueException  indica que foi fornecida uma matriz inválida, deve ter tamanho 4
	*/
	
	public SNMPIPAddress(byte[] enc)
		throws SNMPBadValueException
	{
		
		tag = SNMPBERCodec.SNMPIPADDRESS;
		
		if (enc.length == 4)
		{
			data = enc;
		}
		else		// tamanho errado
		{
			throw new SNMPBadValueException(" IPAddress: bad BER encoding supplied to set value ");
		}
	}
	
	
	
	
	
	/** 
	*	Used to set the value from a byte array containing the address.
	* 	@throws SNMPBadValueException Indicates an incorrect object type supplied, or array of
	*	incorrect size.
	*
	*   usado para configurar os valores de uma matriz de bytes que contém endereço
	*   @throws SNMPBadValueException  indica que um objeto incorreto foi fornecido
	*   ou a matriz tem um tamanho incorreto
	*/
	
	public void setValue(Object newAddress)
		throws SNMPBadValueException
	{
		if ((newAddress instanceof byte[]) && (((byte[])newAddress).length == 4))
			data = (byte[])newAddress;
		else if (newAddress instanceof String)
		{
			data = parseIPAddress((String)newAddress);
		}
		else
			throw new SNMPBadValueException(" IPAddress: bad data supplied to set value ");
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
				returnString += convert;
					
			for (int i = 1; i < data.length; i++)
			{
				convert = data[i];
				if (convert < 0)
					convert += 256;
				returnString += "." + convert;
			}
		}
		
		return returnString;
	}
	
	
	
	
	private byte[] parseIPAddress(String addressString)
		throws SNMPBadValueException
	{
		try
		{
			StringTokenizer st = new StringTokenizer(addressString, " .");
			int size = 0;
			
			while (st.hasMoreTokens())
			{
			   // determina quantos valores estão na string
				size++;
				st.nextToken();
			}
			
			if (size != 4)
			{
				throw new SNMPBadValueException(" IPAddress: wrong number of components supplied to set value ");
			}
			
			byte[] returnBytes = new byte[size];
			
			st = new StringTokenizer(addressString, " .");
			
			for (int i = 0; i < size; i++)
			{
				int addressComponent = (Integer.parseInt(st.nextToken()));
				if ((addressComponent < 0) || (addressComponent > 255))
					throw new SNMPBadValueException(" IPAddress: invalid component supplied to set value ");
				returnBytes[i] = (byte)addressComponent;
			}
			
			return returnBytes;
			
		}
		catch (NumberFormatException e)
		{
			throw new SNMPBadValueException(" IPAddress: invalid component supplied to set value ");
		}
		
	}
	
	
	
	
}
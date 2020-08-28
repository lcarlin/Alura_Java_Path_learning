/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPBERCodec.java
 * Pacote snmp
 * Função Definir os metodos de conversão de codificaçào ASN.1 BER
 *        para subclasse SNMPObject. o processo de extraçào geralmente precede
 *        a estrutura de arvore dos objetos com um objeto SNMPSequence como o Root
 *        Este é o comportamento comum quando mensagem codificada recebida é recebida
 *        por um dispositivo SMNMP
 * Data  05/10/2002
 * Modificação 
 *****************************************************************/


package snmp;


import java.util.*;
import java.io.*;



public class SNMPBERCodec
{
	
	public static final byte SNMPINTEGER = 0x02;
	public static final byte SNMPBITSTRING = 0x03;
	public static final byte SNMPOCTETSTRING = 0x04;
	public static final byte SNMPNULL = 0x05;
	public static final byte SNMPOBJECTIDENTIFIER = 0x06;
	public static final byte SNMPSEQUENCE = 0x30;
	
	public static final byte SNMPIPADDRESS = (byte)0x40;
	public static final byte SNMPCOUNTER32 = (byte)0x41;
	public static final byte SNMPGAUGE32 = (byte)0x42;
	public static final byte SNMPTIMETICKS = (byte)0x43;
	public static final byte SNMPOPAQUE = (byte)0x44;
	public static final byte SNMPNSAPADDRESS = (byte)0x45;
	public static final byte SNMPCOUNTER64 = (byte)0x46;
	public static final byte SNMPUINTEGER32 = (byte)0x47;
	
	public static final byte SNMPGETREQUEST = (byte)0xA0;
	public static final byte SNMPGETNEXTREQUEST = (byte)0xA1;
	public static final byte SNMPGETRESPONSE = (byte)0xA2;
	public static final byte SNMPSETREQUEST = (byte)0xA3;
	public static final byte SNMPTRAP = (byte)0xA4;
	
	
	
	// Constantes SNMPv2. não usada
	public static final byte SNMPv2pCOMMUNICATION = (byte)0xA2;
	public static final byte SNMPv2pAUTHORIZEDMESSAGE = (byte)0xA1;
	public static final byte SNMPv2pENCRYPTEDMESSAGE = (byte)0xA1;
	public static final byte SNMPv2TRAP = (byte)0xA7;
	
	public static final byte SNMPv2pENCRYPTEDDATA = (byte)0xA1;
	
	
	public static final byte SNMPUNKNOWNOBJECT = 0x00;
	
	
	
	
	
	
	/** 
	*   Extrai um objeto SNMP dando o seu tipo, tamanho ,. valor tripo como um 
	*   objeto SNMPTLV. chamada pelas classes construtoras SNMPObject
	*   @throws SNMPBadValueException indica byte de uma matriz no campo valor 
	*   que é interpretado pelo tipo do Objeto SNMP
	*
	*/
   
	public static SNMPObject extractEncoding(SNMPTLV theTLV)
		throws SNMPBadValueException
	{
	
		
		switch (theTLV.tag)
		{
			case SNMPINTEGER:
			{
				return new SNMPInteger(theTLV.value);
			}
			
			case SNMPSEQUENCE:
			{
				return new SNMPSequence(theTLV.value);
			}
			
			case SNMPOBJECTIDENTIFIER:
			{
				return new SNMPObjectIdentifier(theTLV.value);
			}
			
			case SNMPOCTETSTRING:
			{
				return new SNMPOctetString(theTLV.value);
			}
			
			case SNMPIPADDRESS:
			{
				return new SNMPIPAddress(theTLV.value);
			}
			
			case SNMPCOUNTER32:
			{
				return new SNMPCounter32(theTLV.value);
			}
			
			case SNMPGAUGE32:
			{
				return new SNMPGauge32(theTLV.value);
			}
			
			case SNMPTIMETICKS:
			{
				return new SNMPTimeTicks(theTLV.value);
			}
			
			case SNMPNSAPADDRESS:
			{
				return new SNMPNSAPAddress(theTLV.value);
			}
			
			case SNMPCOUNTER64:
			{
				return new SNMPCounter64(theTLV.value);
			}
			
			case SNMPUINTEGER32:
			{
				return new SNMPUInteger32(theTLV.value);
			}
				
			case SNMPGETREQUEST:
			case SNMPGETNEXTREQUEST:
			case SNMPGETRESPONSE:
			case SNMPSETREQUEST:
			{
				return new SNMPPDU(theTLV.value, theTLV.tag);
			}
			
			case SNMPTRAP:
			{
				return new SNMPTrapPDU(theTLV.value);
			}
			
			case SNMPNULL:
			case SNMPOPAQUE:
			{
				return new SNMPNull();
			}
			
			default:
			{
				System.out.println("Unrecognized tag");
				//return new SNMPOctetString(theTLV.value)
				return new SNMPUnknownObject(theTLV.value);
			}
		}
	
	}
	
	
	
	
	
	/** 
	*   Extrai o tipo, tamanho e valor dos objetos SNMP onde a codificaçào BER
	*   começa na posiçào especifica da matriz dada
	*/
	
	public static SNMPTLV extractNextTLV(byte[] enc, int position)
	{
		SNMPTLV nextTLV = new SNMPTLV();
		int currentPos = position;
		
		// pega a TAG
		
		/*
		if ((enc[currentPos] % 32) < 31)
		{
			
			// TAG de byte simples, extraiu o valor
			nextTLV.tag = (int)(enc[currentPos]);
		}
		else
		{
			
			// tag de multiplos bytes,, apenas retorna o valor na subsequencia
			// de bytes
			nextTLV.tag = 0;
			
			do
			{
				currentPos++;
				nextTLV.tag = nextTLV.tag * 128 + (int)(enc[currentPos] % 128);
			}
			while ((enc[currentPos]/128) >= 1);
		}
		*/
		
		// TAG de byte simples, extraiu o valor
		// 
		nextTLV.tag = enc[currentPos];
		currentPos++;	// no comeco da informação do tamanho
		
		// Tamanho do dado
		
		int dataLength;
		
		int unsignedValue = enc[currentPos];
		if (unsignedValue < 0)
			unsignedValue += 256;
			
		if ((unsignedValue / 128) < 1)
		{
			
			// tamanho de byte simples, extrai o valor
			dataLength = unsignedValue;
		}
		else
		{
			
			// byte de tamanho multiplo, primeiro byte de valo ( menos o primeiro bit) é o numero do tamanho de bytes
			int numBytes = (unsignedValue % 128);
			
			dataLength = 0;
			
			for (int i = 0; i < numBytes; i++)
			{
				currentPos++;
				unsignedValue = enc[currentPos];
				if (unsignedValue < 0)
					unsignedValue += 256;
				dataLength = dataLength * 256 + unsignedValue;
			}
		}
		
		
		currentPos++;	// agora no começo do dado
		
		
		// configura o tamanho total
		nextTLV.totalLength = currentPos - position + dataLength;
		
		
		// extrai uma parte  dado
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		outBytes.write(enc, currentPos, dataLength);
		nextTLV.value = outBytes.toByteArray();
				
		
		return nextTLV;
			
	}
	
	
	
	
	/** 
	*  funçào utilizada para codificar um tamanho como uma sequencia BER
	*/
	
	public static byte[] encodeLength(int length)
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
	
		// verificar se pode ser representado num byte simples
		// não se esquecer que o primeiro bir is o bit de "teste de 
		// campos grandes
		if (length < 128)
		{
			byte[] len = {(byte)length};
			outBytes.write(len, 0, 1);
		}
		else
		{
			
			// gardne demais para um byte
			// verificar quantos serão necessários
			int numBytes = 0;
			int temp = length;
			while (temp > 0)
			{
				++numBytes;
				temp = (int)Math.floor(temp / 256);
			}
			
			byte num = (byte)numBytes;
			num += 128;		// configura o bit de formato longo
			outBytes.write(num);
			
			byte[] len = new byte[numBytes];
			for (int i = numBytes-1; i >= 0; --i)
			{
				len[i] = (byte)(length % 256);
				length = (int)Math.floor(length / 256);
			}
			outBytes.write(len, 0, numBytes);
			
		}
		
		return outBytes.toByteArray();
	}
	
	
	
	
}
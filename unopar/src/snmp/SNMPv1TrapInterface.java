/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPv1TrapInterface.java
 * Pacote snmp
 * Função Classe que implementa um listener para as mensagens de trap enviadas
 *        da entidade remota SNMP. o tratamento é que a versão 1 do SNMP, usando
 *        dados criptografados. A comunicaçào ocorre via UDP, usando a porta 162
 *        Porta padrao das TRAPs doSNMP
 * Data  10/10/2002
 * Modificação 
 *****************************************************************/

package snmp;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;



public class SNMPv1TrapInterface
						implements Runnable
{
	public static final int SNMP_TRAP_PORT = 162;
	
	
	// o maior tamanho do pacote do datagrama, baseado 
	// na RFC 1157, precisa manipular mensagens de pelo menos 484 bytes
	public static final int MAXSIZE = 512;
	
	private DatagramSocket dSocket;
	private Thread receiveThread;
	
	private Vector listenerVector;
	
	
	/**
	*   constroi um novo objeto receptor de TRAP à parti do host remoto SNMP
	*   Esta versão aceitará novas mensagens de todas os hosts usando qualquer
	*   nome de comunidade
	*
	*/
	
	
	
	public SNMPv1TrapInterface()
		throws SocketException
	{
		dSocket = new DatagramSocket(SNMP_TRAP_PORT);
		
		listenerVector = new Vector();
		
		receiveThread = new Thread(this);
		
	}
	
	
	
	public void addTrapListener(SNMPTrapListener listener)
	{
		
		// verifica se o listener já foi adicionado, se foi, ignora
		for (int i = 0; i < listenerVector.size(); i++)
		{
			if (listener == listenerVector.elementAt(i))
			{
				return;
			}
		}
		
		
		// se chegar aqui, não há nada na lista, adiciona
		listenerVector.add(listener);
	}
	
	
	
	public void removeTrapListener(SNMPTrapListener listener)
	{
		
		// verifica se o listener está na lista, se está, remove, senão, ignora
		for (int i = 0; i < listenerVector.size(); i++)
		{
			if (listener == listenerVector.elementAt(i))
			{
				listenerVector.removeElementAt(i);
				break;
			}
		}
		
	}

	
	
	/**
	*   Começa a "ouvir" para mensagens de TRAPs
	*/
	
	public void startReceiving()
	{
		
		// se receiveThread  não estiver em execuçào, starta
		if (!receiveThread.isAlive())
		{
			receiveThread = new Thread(this);
			receiveThread.start();
		}
	}
	

	
	
	/**
	*  para de escutar as mensagens de trap
	*/
	
	public void stopReceiving()
		throws SocketException
	{		
		// interrompe o thread de recpçào, que irá morrer naturalmente
		receiveThread.interrupt();
	}

	
	/**
	*   envia a TRAP PDU fornecida para o host. usando o numero de versào 
	*   fornecida e o nome de comunidade. usar o p/ SNMPv1 ou 1 p/ SNMPv2 para capacidades
	*   avancadas proporcionadas pela RFC 1157
	*
	*/
	
	public void sendTrap(int version, InetAddress hostAddress, String community, SNMPTrapPDU pdu)
		throws IOException
	{
		SNMPMessage message = new SNMPMessage(version, community, pdu);
		
		byte[] messageEncoding = message.getBEREncoding();
		
		/*
		System.out.println("Request Message bytes:");
		
		for (int i = 0; i < messageEncoding.length; ++i)
			System.out.print(hexByte(messageEncoding[i]) + " ");
		*/
		
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMP_TRAP_PORT);
		
		/*
		System.out.println("Message bytes length (out): " + outPacket.getLength());
		
		System.out.println("Message bytes (out):");
		for (int i = 0; i < messageEncoding.length; ++i)
		{
			System.out.print(hexByte(messageEncoding[i]) + " ");
		}
		System.out.println("\n");
		*/
		
		dSocket.send(outPacket);
		
	}
	
	
	
	/**
	*	Send the supplied trap pdu to the specified host, using the supplied community name and
	*	using 0 for the version field in the SNMP message (corresponding to SNMP version 1).
	*
	*   envia a TRAP PDU fornecida para o host. usando o numero de versào 
	*   fornecida e o nome de comunidade e usando 0 para o campo de versào
	*   (correspondente  à SNMPv1)
	*
	*/
	
	public void sendTrap(InetAddress hostAddress, String community, SNMPTrapPDU pdu)
		throws IOException
	{
		int version = 0;
	
		sendTrap(version, hostAddress, community, pdu);
	}
	
	
	
	/**
	*  metodo run() para a interface do TRap Listener. apenas espera por mensagens
	*  de trap chegarem pela porta 162, então envia a SNMPTrapPDU recuperada para cada
	*  um dos SNMPTrapListeners registrados, chamando seus metodos processTrap()
	*/
	
	public void run()
	{
		
		int errorStatus = 0;
		int errorIndex = 0;
		
		
		try
		{
		
			while (!receiveThread.isInterrupted())
			{
				
				DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
				dSocket.receive(inPacket);
				
				byte[] encodedMessage = inPacket.getData();
				
				
				/*
				System.out.println("Message bytes length (in): " + inPacket.getLength());
				
				System.out.println("Message bytes (in):");
				for (int i = 0; i < encodedMessage.length; ++i)
				{
					System.out.print(hexByte(encodedMessage[i]) + " ");
				}
				System.out.println("\n");
				*/
				
				
				SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
				
				SNMPTrapPDU receivedPDU = receivedMessage.getTrapPDU();
				
				
				// passa as TRAP PDU recebiudas para o metodo processTrap de qualquer listener
				for (int i = 0; i < listenerVector.size(); i++)
				{
					SNMPTrapListener listener = (SNMPTrapListener)listenerVector.elementAt(i);
					
					listener.processTrap(receivedPDU);
				}
				
			}
		
		}
		catch (IOException e)
		{
			
			// não implementado ainda...
		}
		catch (SNMPBadValueException e)
		{
			// não implementado ainda...
		}
				
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
	
	
	
	
	
	
	private String getHex(byte theByte)
	{
		int b = theByte;
		
		if (b < 0)
			b += 256;
		
		String returnString = new String(Integer.toHexString(b));
		
		
		// adiciona o 0 precedente se necessário
		if (returnString.length()%2 == 1)
			returnString = "0" + returnString;
			
		return returnString;
	}
	
	
	
}
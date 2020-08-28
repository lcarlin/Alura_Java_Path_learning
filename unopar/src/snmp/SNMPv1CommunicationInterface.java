/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPv1CommunicationInterface.java
 * Pacote snmp
 * Função Classe que define metodos para comunicaçào com entidades SNMP
 *        Aproximada da versào 1 do SNMP, que usa dados criptoghrafados.
 *        A comunicacão ocorre Via UDP, usando a porta padrào 161
 * Data  09/10/2002
 * Modificação 
 *****************************************************************/


package snmp;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;




public class SNMPv1CommunicationInterface
{
	public static final int SNMPPORT = 161;
	
	// maior tamanho do pacote de datagrama, baseado na RFC 1157
	// precisa manipular mensagens com pelo menos 484 bytes
	public static final int MAXSIZE = 512;
	
	private int version;
	private InetAddress hostAddress;
	private String community;
	DatagramSocket dSocket;
	
	public int requestID = 1;
			
	
	
	
	/**
	*   Constroi um novo objeto de comunicaçào com o Host especifico, usando o nome 
	*   de comunidade fornecido. Os parametros de versão devem ser 0 ( V1) e 1 ( v2), conf RFC 1157
	*/
	
	public SNMPv1CommunicationInterface(int version, InetAddress hostAddress, String community)
		throws SocketException
	{
		this.version = version;
		this.hostAddress = hostAddress;
		this.community = community;
		
		dSocket = new DatagramSocket();
		dSocket.setSoTimeout(15000);	//15 segundos
	}
	
	
	
	
	/**
	*   Permite setar os valores de Timeou para os datagramas de socktes ( em milisegundos)
	*/
	
	public void setSocketTimeout(int socketTimeout)
		throws SocketException
	{
		dSocket.setSoTimeout(socketTimeout);
	}
	
	
	
	/**
	*   fecha a conexão com o dispositivo
	*/
	
	public void closeConnection()
		throws SocketException
	{
		dSocket.close();
	}

	
	
	
	
	
	/**
	*	Retrieve all MIB variable values subsequent to the starting object identifier
	*	given in startID (in dotted-integer notation). Return as SNMPVarBindList object.
	*	Uses SNMPGetNextRequests to retrieve variable values in sequence.
	*	@throws IOException Thrown when timeout experienced while waiting for response to request.
	*	@throws SNMPBadValueException 
	*
	*   recupera todas os valores das variaveis MIB subsequentes para inicializar o identificador de objetos
	*   dado na startID (na notaçào de pontos e inteiros). retorna como objeto SNMPVarBindList
	*   Usa SNMPGetNextRequest para recuperar valores de variaveis em sequencia.
	*	@throws IOException Thrown quando expirou o tempo de espera enquanto aguarda por resposta de requisiçào
	*	@throws SNMPBadValueException 
	*
	*/
	
	public SNMPVarBindList retrieveAllMIBInfo(String startID)
		throws IOException, SNMPBadValueException
	{
		//envia GetNextRequesnt até receber
		// uma mensagem de erro ou repete identificadores que foram enviados
		SNMPVarBindList retrievedVars = new SNMPVarBindList();
		
		
		int errorStatus = 0;
		int errorIndex = 0;
		
		SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(startID);
		SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, new SNMPInteger(0));
		SNMPSequence varList = new SNMPSequence();
		varList.addSNMPObject(nextPair);
		SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPGETNEXTREQUEST, requestID, errorStatus, errorIndex, varList);
		SNMPMessage message = new SNMPMessage(version, community, pdu);
		byte[] messageEncoding = message.getBEREncoding();
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
		
		
		dSocket.send(outPacket);
		
		
		
		while (errorStatus == 0)
		{
			
			DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
			dSocket.receive(inPacket);
			
			byte[] encodedMessage = inPacket.getData();
			
			
			SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
			//errorStatus = ((BigInteger)((SNMPInteger)((receivedMessage.getPDU()).getSNMPObjectAt(1))).getValue()).intValue();
			
			
			varList = (receivedMessage.getPDU()).getVarBindList();
			SNMPSequence newPair = (SNMPSequence)(varList.getSNMPObjectAt(0));
			
			SNMPObjectIdentifier newObjectIdentifier = (SNMPObjectIdentifier)(newPair.getSNMPObjectAt(0));
			SNMPObject newValue = newPair.getSNMPObjectAt(1);
			
			retrievedVars.addSNMPObject(newPair);
			
			
			if (requestedObjectIdentifier.equals(newObjectIdentifier))
				break;
				
			requestedObjectIdentifier = newObjectIdentifier;
		
			requestID++;
			pdu = new SNMPPDU(SNMPBERCodec.SNMPGETNEXTREQUEST, requestID, errorStatus, errorIndex, varList);
			message = new SNMPMessage(version, community, pdu);
			messageEncoding = message.getBEREncoding();
			outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
			
			dSocket.send(outPacket);
			
		}
			
		
		return retrievedVars;
		
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
	 * Recupera o valor da variavel MIB correspondente ao identificador de objetos
	 * fornecido em itemID (NA NOTAÇÀO PONTO-INTEIR) retorna como objeto SNMPVarBindList;
	 * se não tal variavel ( se o dispositivo não suportar SNMP, ou tiver um nome de comunidade incorreto)
	 * a variavel será um objeto SNMPNull 
	 * @throws IOException Thrown quando expira o tempo de expera enquanto aguarda pela resposta da requisiçào
	 * @throws SNMPBadValueException 
	 *
	 *
	 */
	
	public SNMPVarBindList getMIBEntry(String itemID)
		throws IOException, SNMPBadValueException, SNMPGetException
	{
		
		// envia GetRequest para o Host especifico para recuperar um identificador de objetos espeficio
		
		SNMPVarBindList retrievedVars = new SNMPVarBindList();
		
		
		int errorStatus = 0;
		int errorIndex = 0;
		
		
		SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(itemID);
		SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, new SNMPInteger(0));
		SNMPSequence varList = new SNMPSequence();
		varList.addSNMPObject(nextPair);
		SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPGETREQUEST, requestID, errorStatus, errorIndex, varList);
		
		SNMPMessage message = new SNMPMessage(version, community, pdu);
		
		byte[] messageEncoding = message.getBEREncoding();
		
		
		/*
		System.out.println("Request Message bytes:");
		
		for (int i = 0; i < messageEncoding.length; ++i)
			System.out.print(hexByte(messageEncoding[i]) + " ");
		*/
		
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
		
		
		dSocket.send(outPacket);
		
		DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
		
		// Aguarda até receber uma resposta para requestID e OID ( ou erro )
		while (true)	
		{
			
			dSocket.receive(inPacket);
			
			byte[] encodedMessage = inPacket.getData();
			
			/*
			System.out.println("Message bytes:");
			
			for (int i = 0; i < encodedMessage.length; ++i)
			{
				System.out.print(hexByte(encodedMessage[i]) + " ");
			}
			*/
			
			
			SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
			SNMPPDU receivedPDU = receivedMessage.getPDU();
			
			
			// verifica a identificador solicitado. se incorreto, apenas ignora o pacote e continua a espera
			if (receivedPDU.getRequestID() == requestID)
			{
				
				
				// verifica o status de erro, se for problema de recuperaçào, Lança a excessão SNMPGetException
				if (receivedPDU.getErrorStatus() != 0)
					throw new SNMPGetException("OID " + itemID + " not available for retrieval");		
				
				
				varList = receivedPDU.getVarBindList();
				SNMPSequence newPair = (SNMPSequence)(varList.getSNMPObjectAt(0));
				
				SNMPObjectIdentifier newObjectIdentifier = (SNMPObjectIdentifier)(newPair.getSNMPObjectAt(0));
				SNMPObject newValue = newPair.getSNMPObjectAt(1);
				
				// verifica o identificador de objeto para ter certeza de que a variavel correta foi recebida.
				// se não, apenas continua esperando para receber
				if (newObjectIdentifier.toString().equals(itemID))
				{
					// pega o correto, adiciona-o à lista de veriaveis e cai fora
					retrievedVars.addSNMPObject(newPair);
					break;
				}
			
			}
			
		}
		
		
		requestID++;
		
		
		return retrievedVars;
		
	}
	
	

	/**
	 * Recupera o valor da variavel MIB correspendente ao identificador de objetos
	 * fornecido em itemID (NA NOTAÇÀO PONTO-INTEIR) retorna como objeto SNMPVarBindList;
	 * se não tal variavel ( se o dispositivo não suportar SNMP, ou tiver um nome de comunidade incorreto)
	 * a variavel será um objeto SNMPNull 
	 * @throws IOException Thrown quando expira o tempo de expera enquanto aguarda pela resposta da requisiçào
	 * @throws SNMPBadValueException 
	 *
	 *
	 */
	
	public SNMPVarBindList getNextMIBEntry(String itemID)
		throws IOException, SNMPBadValueException, SNMPGetException
	{
		// envia GetRequest para o host especifico para recuperar um identificador de Objetos especifico
		
		SNMPVarBindList retrievedVars = new SNMPVarBindList();
		
		
		int errorStatus = 0;
		int errorIndex = 0;
		
		
		SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(itemID);
		SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, new SNMPInteger(0));
		SNMPSequence varList = new SNMPSequence();
		varList.addSNMPObject(nextPair);
		SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPGETNEXTREQUEST, requestID, errorStatus, errorIndex, varList);
		
		SNMPMessage message = new SNMPMessage(version, community, pdu);
		
		byte[] messageEncoding = message.getBEREncoding();
		
		
		/*
		System.out.println("Request Message bytes:");
		
		for (int i = 0; i < messageEncoding.length; ++i)
			System.out.print(hexByte(messageEncoding[i]) + " ");
		*/
		
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
		
		
		dSocket.send(outPacket);
		
		DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
		// espera até receber uma resposta para requestID e OID ( ou erro)
		while (true)	
		{
			
			dSocket.receive(inPacket);
			
			byte[] encodedMessage = inPacket.getData();
			
			/*
			System.out.println("Message bytes:");
			
			for (int i = 0; i < encodedMessage.length; ++i)
			{
				System.out.print(hexByte(encodedMessage[i]) + " ");
			}
			*/
			
			
			SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
			SNMPPDU receivedPDU = receivedMessage.getPDU();
			
			
			// Verifica o identificador de requisição,  incorreto, apenas ignora o pacote e continua a espera
			if (receivedPDU.getRequestID() == requestID)
			{
				
				// verifica o status de erro, se ohouver erro na recuperaçào, lançà SNMPGetException
				if (receivedPDU.getErrorStatus() != 0)
					throw new SNMPGetException("OID " + itemID + " not available for retrieval");		
				
				
				varList = receivedPDU.getVarBindList();
				SNMPSequence newPair = (SNMPSequence)(varList.getSNMPObjectAt(0));
				
				SNMPObjectIdentifier newObjectIdentifier = (SNMPObjectIdentifier)(newPair.getSNMPObjectAt(0));
				SNMPObject newValue = newPair.getSNMPObjectAt(1);
				
				retrievedVars.addSNMPObject(newPair);
				
				break;
			
			}
			
		}
		
		
		requestID++;
		
		
		return retrievedVars;
		
	}
	
	

	/***
	 * configura a o valor da variavel MIB do identificador de objetos dado no startID (NA NOTAÇÀO PONTO-INTEIR)
	 * retorna o objeto SNMPVarBindList retornado pelo dispositivo na sua resposta. pode ser utilizado para checar
	 * se a configuraçào foi bem-sucedida. 
	 * utiliza SNMPGetNextRequests para recuperar valores de variaveis em sequencia.
	 * @throws IOException Thrown when quando expira o tempo de expera enquanto aguarda pela resposta da requisiçào
	 * @throws SNMPBadValueException 
	 *
	 *
	 */
	
	public SNMPVarBindList setMIBEntry(String itemID, SNMPObject newValue)
		throws IOException, SNMPBadValueException, SNMPSetException
	{
		// envia SetRequest para o Host Especifico para configurar o identificador de objetos especifico
		
		SNMPVarBindList retrievedVars = new SNMPVarBindList();
		
		int errorStatus = 0;
		int errorIndex = 0;
		
		SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(itemID);
		SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, newValue);
		
			
		
		SNMPSequence varList = new SNMPSequence();
		varList.addSNMPObject(nextPair);
		SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPSETREQUEST, requestID, errorStatus, errorIndex, varList);
		
		
		SNMPMessage message = new SNMPMessage(version, community, pdu);
		byte[] messageEncoding = message.getBEREncoding();
		
		/*
		System.out.println("Message bytes:");
		
		for (int i = 0; i < messageEncoding.length; ++i)
		{
			System.out.print(getHex(messageEncoding[i]) + " ");
		}
		*/
		
		
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
		
		
		dSocket.send(outPacket);
		
		
		DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
		// espera até receber uma resposta para requestID e OID ( ou erro)
		while (true)	
		{
			
			dSocket.receive(inPacket);
			
			
			byte[] encodedMessage = inPacket.getData();
			
			/*
			System.out.println("Message bytes:");
			
			for (int i = 0; i < encodedMessage.length; ++i)
			{
				System.out.print((encodedMessage[i]) + " ");
			}
			*/
		
		
			
			SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
			
			SNMPPDU receivedPDU = receivedMessage.getPDU();
			
			
			
			// verifica a requisiçào de identificador, se incorreto, apenas ignora o pacote e continua esperando
			if (receivedPDU.getRequestID() == requestID)
			{
				
				
				// Verifica o status de erro, se for probelma de recuperaçào, lança SNMPGetException
				if (receivedPDU.getErrorStatus() != 0)
				{
					switch (receivedPDU.getErrorStatus())
					{
						case 1:
							throw new SNMPSetException("Value supplied for OID " + itemID + " too big.");
						
						case 2:
							throw new SNMPSetException("OID " + itemID + " not available for setting.");
						
						case 3:
							throw new SNMPSetException("Bad value supplied for OID " + itemID + ".");
							
						case 4:
							throw new SNMPSetException("OID " + itemID + " read-only.");
							
						default:
							throw new SNMPSetException("Error setting OID " + itemID + ".");	
							
					}
				}
				
				
				varList = receivedPDU.getVarBindList();
				SNMPSequence newPair = (SNMPSequence)(varList.getSNMPObjectAt(0));
				
				// verifica o identificador de objeto para ter certeza de que a variavel correta foi recebida.
				// se não, apenas continua esperando para receber
				if (((SNMPObjectIdentifier)newPair.getSNMPObjectAt(0)).toString().equals(itemID))
				{
					
					// Pega o certo, e o adiciona lista de variaveis recuperadas e para 
					retrievedVars.addSNMPObject(newPair);
					break;
				}
			
			}
			
		}
		
		
		requestID++;
	
		
		return retrievedVars;
		
	}
	
	
	/*
	public void broadcastDiscovery(String itemID)
		throws IOException, SNMPBadValueException
	{
		// send GetRequest to all hosts to retrieve specified object identifier
		
	
		int errorStatus = 0;
		int errorIndex = 0;
		
		int requestID = 0;
		SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(itemID);
		SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, new SNMPInteger(0));
		SNMPSequence varList = new SNMPSequence();
		varList.addSNMPObject(nextPair);
		SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPGETREQUEST, requestID, errorStatus, errorIndex, varList);
		SNMPMessage message = new SNMPMessage(0, community, pdu);
		byte[] messageEncoding = message.getBEREncoding();
		DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
		
		
		dSocket.send(outPacket);
		
		
		
	}
	
	
	
	
	public String receiveDiscovery()
		throws IOException, SNMPBadValueException
	{
		// receive responses from hosts responding to discovery message
		
		int MAXSIZE = 512;
			
		String returnString = new String();
		
		int errorStatus = 0;
		int errorIndex = 0;
		int requestID = 0;
		
		
		DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
		
		dSocket.receive(inPacket);
		String hostString = inPacket.getAddress().toString();
		returnString += "Packet received from: " + hostString + "\n";
		
		byte[] encodedMessage = inPacket.getData();
		
		
		returnString += "Message bytes:" + "\n";
		
		for (int i = 0; i < encodedMessage.length; ++i)
		{
			returnString += (encodedMessage[i]) + " ";
		}
		
		
		SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
		SNMPSequence varList = (receivedMessage.getPDU()).getVarBindList();
		SNMPSequence newPair = (SNMPSequence)(varList.getSNMPObjectAt(0));
		SNMPObject newValue = newPair.getSNMPObjectAt(1);
		
		// return just value string
		returnString += newValue.toString() + "\n\n";
		
		
		
		returnString += "Received message contents:\n";
		returnString += receivedMessage.toString() + "\n";
		System.out.println(receivedMessage.toString());
		
	
		
		return returnString;
		
	}
	
	
	
	
	public String discoverDevices(String itemID)
		throws IOException, SNMPBadValueException
	{
		// send GetRequest to all hosts to retrieve specified object identifier
		
		int MAXSIZE = 512;
		
			
		String returnString = new String();
		
		try
		{
			int errorStatus = 0;
			int errorIndex = 0;
			
			DatagramSocket dSocket = new DatagramSocket();
			
			int requestID = 0;
			SNMPObjectIdentifier requestedObjectIdentifier = new SNMPObjectIdentifier(itemID);
			SNMPVariablePair nextPair = new SNMPVariablePair(requestedObjectIdentifier, new SNMPInteger(0));
			SNMPSequence varList = new SNMPSequence();
			varList.addSNMPObject(nextPair);
			SNMPPDU pdu = new SNMPPDU(SNMPBERCodec.SNMPGETREQUEST, requestID, errorStatus, errorIndex, varList);
			SNMPMessage message = new SNMPMessage(0, community, pdu);
			byte[] messageEncoding = message.getBEREncoding();
			DatagramPacket outPacket = new DatagramPacket(messageEncoding, messageEncoding.length, hostAddress, SNMPPORT);
			
			
			dSocket.send(outPacket);
			
			
			DatagramPacket inPacket = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
			
			while (true)
			{
				dSocket.receive(inPacket);
				String hostString = inPacket.getAddress().toString();
				returnString += "Packet received from: " + hostString + "\n";
				
				byte[] encodedMessage = inPacket.getData();
				
				
				returnString += "Message bytes:" + "\n";
				
				for (int i = 0; i < encodedMessage.length; ++i)
				{
					returnString += (encodedMessage[i]) + " ";
				}
				
				
				SNMPMessage receivedMessage = new SNMPMessage(SNMPBERCodec.extractNextTLV(encodedMessage,0).value);
				returnString += "Received message contents:\n";
				returnString += receivedMessage.toString() + "\n";
				
	
			}
		}
		catch (Exception e)
		{
		
		}
		
		return returnString;
		
	}
	
	
	
	private String getHex(byte theByte)
	{
		int b = theByte;
		
		if (b < 0)
			b += 256;
		
		String returnString = new String(Integer.toHexString(b));
		
		// add leading 0 if needed
		if (returnString.length()%2 == 1)
			returnString = "0" + returnString;
			
		return returnString;
	}
	
	*/
	
	
	
}
/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPMessage.java
 * Pacote snmp
 * Fun��o define a classe SNMPMEssage como uma classe especia de 
 *         SNMPSequence, definido as mensagens de alto-nivel do SNMP
 *         como as defini��es da RFC 1157 e RFC 1091
 * Data  10/10/2002
 * Modifica��o 
 *****************************************************************/


package snmp;

import java.util.*;



/**



RFC1157-SNMP DEFINITIONS

     IMPORTS FROM RFC1155-SMI;

     -- top-level message

             Message ::=
                     SEQUENCE {
                          version        -- version-1 for this RFC
                             INTEGER {
                                 version-1(0)
                             },

                         community      -- community name
                             OCTET STRING,

                         data           -- e.g., PDUs if trivial
                             ANY        -- authentication is being used
                     }
                     
                     
  -- From RFC 1901:
  
  COMMUNITY-BASED-SNMPv2 DEFINITIONS ::= BEGIN

    -- top-level message

        Message ::=
                SEQUENCE {
                     version
                        INTEGER {
                            version(1)  -- modified from RFC 1157
                        },

                    community           -- community name
                        OCTET STRING,

                    data                -- PDUs as defined in [4]
                        ANY
                }
        }

    END                   

*/


public class SNMPMessage extends SNMPSequence
{
	
	
	/**
	*   Cria umamensagem SNMP com a vers�o especifica, comunidade e PDU
	*   usado 0 p/ SNMP v1 e 1 p/ SNMPv2
	*/
	
	public SNMPMessage(int version, String community, SNMPPDU pdu)
	{
		super();
		Vector contents = new Vector();
		contents.insertElementAt(new SNMPInteger(version), 0);
		contents.insertElementAt(new SNMPOctetString(community), 1);
		contents.insertElementAt(pdu, 2);
		
		try
		{
			this.setValue(contents);
		}
		catch (SNMPBadValueException e)
		{			
		// n�o pode ocorrer, todos vetores de elementos fornecidos s�o subclasses do SNMPObject
		}
	}
	
	
	
	/**
	*   Cria umamensagem SNMP com a vers�o especifica, comunidade e PDU
	*   usado 0 p/ SNMP v1 e 1 p/ SNMPv2
	*/
	
	public SNMPMessage(int version, String community, SNMPTrapPDU pdu)
	{
		super();
		Vector contents = new Vector();
		contents.insertElementAt(new SNMPInteger(version), 0);
		contents.insertElementAt(new SNMPOctetString(community), 1);
		contents.insertElementAt(pdu, 2);
		
		try
		{
			this.setValue(contents);
		}
		catch (SNMPBadValueException e)
		{
	// n�o pode ocorrer, todos vetores de elementos fornecidos s�o subclasses do SNMPObject
		}
	}
	
	
	
	
	/**
	*   Constroi uma SNMPMessage � partir de um byte recebido na representa��o ASN.1 
	*   @throws SNMPBadValueException indica codifica��o de mensagem i'nv�lida
	*/
	
	protected SNMPMessage(byte[] enc)
		throws SNMPBadValueException
	{
		super(enc);
	}
	
	
	
	/** 
	*   Metodo utili�rio que retorna a PDU que contida na mensagem SNMP. 
	*   A PDU 's o terceiro omponete da sequencia, ap�s a vers�o e o nome da
	*   comunidade
	*/
	
	public SNMPPDU getPDU()
		throws SNMPBadValueException
	{
		Vector contents = (Vector)(this.getValue());
		Object pdu = contents.elementAt(2);
		
		if (!(pdu instanceof SNMPPDU))
		{
			throw new SNMPBadValueException("Wrong PDU type in message: expected SNMPPDU, have " + pdu.getClass().toString());
		}
		
		return (SNMPPDU)pdu;
	}
	
	
	public SNMPTrapPDU getTrapPDU()
		throws SNMPBadValueException
	{
		Vector contents = (Vector)(this.getValue());
		Object pdu = contents.elementAt(2);
		
		if (!(pdu instanceof SNMPTrapPDU))
		{
			throw new SNMPBadValueException("Wrong PDU type in message: expected SNMPTrapPDU, have " + pdu.getClass().toString());
		}
		
		return (SNMPTrapPDU)pdu;
	}
	
}
/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPTrapPDU.java
 * Pacote snmp
 * Função Classe que representa uma trap SNMP PDU (Protocol Data Units)
 *        conf RFC 1157, indicado no codigo. 
 * Data  10/10/2002
 * Modificação 
 *****************************************************************/


package snmp;

import java.util.*;
import java.io.*;
import java.math.*;




/**

-- protocol data units

          PDUs ::=
                  CHOICE {
                              get-request
                                  GetRequest-PDU,

                              get-next-request
                                  GetNextRequest-PDU,

                              get-response
                                  GetResponse-PDU,

                              set-request
                                  SetRequest-PDU,

                              trap
                                  Trap-PDU
                          }

          -- PDUs

          GetRequest-PDU ::=
              [0]
                  IMPLICIT PDU

          GetNextRequest-PDU ::=
              [1]
                  IMPLICIT PDU

          GetResponse-PDU ::=
              [2]
                  IMPLICIT PDU

          SetRequest-PDU ::=
              [3]
                  IMPLICIT PDU

          PDU ::=
                  SEQUENCE {
                     request-id
                          INTEGER,

                      error-status      -- sometimes ignored
                          INTEGER {
                              noError(0),
                              tooBig(1),
                              noSuchName(2),
                              badValue(3),
                              readOnly(4),
                              genErr(5)
                          },

                      error-index       -- sometimes ignored
                         INTEGER,

                      variable-bindings -- values are sometimes ignored
                          VarBindList
                  }

          
		  Trap-PDU ::=
              [4]
                 IMPLICIT SEQUENCE {
                      enterprise        -- type of object generating
                                        -- trap, see sysObjectID in [5]

                          OBJECT IDENTIFIER,

                      agent-addr        -- address of object generating
                          NetworkAddress, -- trap

                      generic-trap      -- generic trap type
                          INTEGER {
                              coldStart(0),
                              warmStart(1),
                              linkDown(2),
                              linkUp(3),
                              authenticationFailure(4),
                              egpNeighborLoss(5),
                              enterpriseSpecific(6)
                          },

                      specific-trap  -- specific code, present even
                          INTEGER,   -- if generic-trap is not
                                     -- enterpriseSpecific

                      time-stamp     -- time elapsed between the last
                          TimeTicks, -- (re)initialization of the
                                        network
                                     -- entity and the generation of the
                                        trap

                       variable-bindings -- "interesting" information
                          VarBindList
                  }
          -- variable bindings

          VarBind ::=
                  SEQUENCE {
                      name
                          ObjectName,

                      value
                          ObjectSyntax
                  }

         VarBindList ::=
                  SEQUENCE OF
                     VarBind

         END

*/


public class SNMPTrapPDU extends SNMPSequence
{
	
	/*
	* Cria uma nova TRAP PDU do tipo espeficio, com u request ID fornecido, status de erro e indice de erro, 
	* além de conter a sequencia de dados fornecidos pelo SNMP
	**/
	
	public SNMPTrapPDU(SNMPObjectIdentifier enterpriseOID, SNMPIPAddress agentAddress, int genericTrap, int specificTrap, SNMPTimeTicks timestamp, SNMPSequence varList)
		throws SNMPBadValueException
	{
		super();
		
		tag = SNMPBERCodec.SNMPTRAP;
		
		Vector contents = new Vector();
		
		contents.addElement(enterpriseOID);
		contents.addElement(agentAddress);
		contents.addElement(new SNMPInteger(genericTrap));
		contents.addElement(new SNMPInteger(specificTrap));
		contents.addElement(timestamp);
		contents.addElement(varList);
		
		this.setValue(contents);
	}
	
	
	/*
	* Cria uma nova TRAP PDU do tipo espeficio, com u request ID fornecido, status de erro e indice de erro, 
	* além de conter umaa sequencia SNMP vazia (VarBinList0 como dados adicionais
	**/
	
	public SNMPTrapPDU(SNMPObjectIdentifier enterpriseOID, SNMPIPAddress agentAddress, int genericTrap, int specificTrap, SNMPTimeTicks timestamp)
		throws SNMPBadValueException
	{
		super();
		
		tag = SNMPBERCodec.SNMPTRAP;
		
		Vector contents = new Vector();
		
		contents.addElement(enterpriseOID);
		contents.addElement(agentAddress);
		contents.addElement(new SNMPInteger(genericTrap));
		contents.addElement(new SNMPInteger(specificTrap));
		contents.addElement(timestamp);
		contents.addElement(new SNMPVarBindList());
		
		this.setValue(contents);
	}
	
	

	/*
	* Cria uma nova PDu do tipo espeficicado pela codificaçào BER fornecida
	* @throws SNMPBadValueException indica codificaçào invalida SNMP PDU fornecia pela codificaçào
	**/
	
	
	protected SNMPTrapPDU(byte[] enc)
		throws SNMPBadValueException
	{
		tag = SNMPBERCodec.SNMPTRAP;
		extractFromBEREncoding(enc);
	}
	
	
	/* Metodo util que extrai uma lista de binding variavel  do PDU. Util para receber um conjunto 
	 *  (identificador de Objetos, valores) de paes retornados em respoata à requisiçào à um dispositivo SNMP
	 *  A Lista de binding variaveis é apenas uma sequencia SNMP que contém o identificado, valoers pares
	 *  @ Veja  snmp.SNMPVarBindList
	 */
	
	public SNMPSequence getVarBindList()
	{
		Vector contents = (Vector)(this.getValue());
		return (SNMPSequence)(contents.elementAt(5));
	}
	
	
	
	
	/**
	 * metodo utilitário qye extrai o Enterprise OID deste PDU
	 */
	
	public SNMPObjectIdentifier getEnterpriseOID()
	{
		Vector contents = (Vector)(this.getValue());
		return (SNMPObjectIdentifier)contents.elementAt(0);
	}
	
		
	/**
	 * Metodo utiliário que extrai o endereço do agente que envia esta PDU
	 */
	
	public SNMPIPAddress getAgentAddress()
	{
		Vector contents = (Vector)(this.getValue());
		return (SNMPIPAddress)contents.elementAt(1);
	}
	
	
	 /*
	 * Metodo utiliário que retorna o código generico da trap parta esta PDU
	 */
	public int getGenericTrap()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPInteger)(contents.elementAt(2))).getValue()).intValue();
	}
	
	
	/** 
	*   Metodo utilitario qye retorna o código esp[ecficio da trap para este PDU
	*/
	
	public int getSpecificTrap()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPInteger)(contents.elementAt(3))).getValue()).intValue();
	}
	
	
	/** 
	*  Metodo utilitáio que retorna o Timestamp para esta PDU
	*/
	
	public long getTimestamp()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPTimeTicks)(contents.elementAt(4))).getValue()).longValue();
	}
	
	
}
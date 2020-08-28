/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPPDU.java
 * Pacote snmp
 * Função Representa uma SNMP PDU da RFC 1157, como indicado no código
 *        fonte
 * Data  01/10/2002
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


public class SNMPPDU extends SNMPSequence
{
	
	
	/**
	*   Cria umja nova PDU do tipo especifico, como a Request Id fornecida, status de erro
	*   e o indice de erro. além de conter as sequencias de dados SNMP fornecidas
	*/
	
	public SNMPPDU(byte pduType, int requestID, int errorStatus, int errorIndex, SNMPSequence varList)
		throws SNMPBadValueException
	{
		super();
		Vector contents = new Vector();
		tag = pduType;
		contents.insertElementAt(new SNMPInteger(requestID), 0);
		contents.insertElementAt(new SNMPInteger(errorStatus), 1);
		contents.insertElementAt(new SNMPInteger(errorIndex), 2);
		contents.insertElementAt(varList, 3);
		this.setValue(contents);
	}
	
	
	
	
	/*
	*   Cria uma nova PDU do tipo especifico fornecido pela codificação BER
	*   @throws SNMPBadValueException indica que foi fornecida uma coficiaçào
	*        BER SNMP inválida
	*/
	
	protected SNMPPDU(byte[] enc, byte pduType)
		throws SNMPBadValueException
	{
		tag = pduType;
		extractFromBEREncoding(enc);
	}
	
	
	
	
	/** 
	*   Metodo qye extrai as lista de variaveis Binding  da PDU. utilizada para recuperar
	*   o conjunto de objetos pares restornados em resposta a uma request à um disposiutivo SNMP
	*   A lista de variaveis BINDing é apenas uma sequencia que contém o identificador
	*   Consultar snmp.SNMPVarBindList
	*
	*/
	
	public SNMPSequence getVarBindList()
	{
		Vector contents = (Vector)(this.getValue());
		return (SNMPSequence)(contents.elementAt(3));
	}
	
	
	
	/** 
	*  Metodo utiliário para extrair os numeros Request ID deste PDU
	*/
	
	public int getRequestID()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPInteger)(contents.elementAt(0))).getValue()).intValue();
	}
	
	
	
	/** 
	*   Metodo utiliário qye extrai o statuis de erro desta PDU, se maior que zero, podemos extrair 
	*   a variavel com problema usando  getErrorIndex()
	*
	*/
	
	public int getErrorStatus()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPInteger)(contents.elementAt(1))).getValue()).intValue();
	}
	
	
	
	/** 
	*   Metodo utilitário que retorna o indice de erro pra esta PDU, identificando 
	*   a variavel com problema
	*
	*/
	
	
	public int getErrorIndex()
	{
		Vector contents = (Vector)(this.getValue());
		return ((BigInteger)((SNMPInteger)(contents.elementAt(2))).getValue()).intValue();
	}
	
	
}
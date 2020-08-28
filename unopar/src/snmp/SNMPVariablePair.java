/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInteger32.java
 * Pacote snmp
 * Função classe que é implementa a especificaçào VarBind detalhada conf. RFC 1157
 *        é uma especializaçào de SNMPSenquence, definindo uma sequencia de 2 elementos
 *        contendo um par simples(Identificador de objetos e valor)
 * Data  13/10/2002
 * Modificação 
 ****************************************************************/

package snmp;

import java.util.*;




/**
          -- variable bindings

          VarBind ::=
                  SEQUENCE {
                      name
                          ObjectName,

                      value
                          ObjectSyntax
                  }



*/


public class SNMPVariablePair extends SNMPSequence
{
	
	/**
	*   Cria um novo par de variavels tendo o valor fornecido pelo Identificador de Objetos
	*/
	
	public SNMPVariablePair(SNMPObjectIdentifier objectID, SNMPObject value)
		throws SNMPBadValueException
	{
		super();
		Vector contents = new Vector();
		contents.insertElementAt(objectID, 0);
		contents.insertElementAt(value, 1);
		this.setValue(contents);
	}

	
	
}
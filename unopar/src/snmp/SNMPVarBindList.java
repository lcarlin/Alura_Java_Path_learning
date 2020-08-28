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
 * Função classe que é uma especialização da classe SNMPSequence que contém
 *uma lista dos objetos SNMPVariablePair, como no codigo abaixo em comentário
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

         VarBindList ::=
                  SEQUENCE OF
                     VarBind

         END

*/

public class SNMPVarBindList extends SNMPSequence
{
	
	/** 	
	*   cria uma nova lista vazia de variavel binding
	*/
	
	
	public SNMPVarBindList()
	{
		super();
	}
	
		
	
	
	/**
	*   Retorna o par de variaveis na lista, separados por linhas
	*/
	
	public String toString()
	{
		Vector sequence = (Vector)(this.getValue());
		
		String valueString = new String();
		
		for (int i = 0; i < sequence.size(); ++i)
		{
			valueString += ((SNMPObject)sequence.elementAt(i)).toString() + "\n";
		}
		
		return valueString;
	}
	
	
	
}
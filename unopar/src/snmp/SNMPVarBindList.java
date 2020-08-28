/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInteger32.java
 * Pacote snmp
 * Fun��o classe que � uma especializa��o da classe SNMPSequence que cont�m
 *uma lista dos objetos SNMPVariablePair, como no codigo abaixo em coment�rio
 * Data  13/10/2002
 * Modifica��o 
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
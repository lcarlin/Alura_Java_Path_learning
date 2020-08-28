/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPBadValueException.java
 * Pacote snmp
 * Fun��o excess�o lan�ada quando tenta criar uma subclase SNMPObject
 *        com um dado inapropriado, ou configurar seus valores com dados
 *        inapropriados
 * Data  14/10/2002
 * Modifica��o 
 ****************************************************************/



package snmp;


public class SNMPBadValueException extends Exception
{
	
	public SNMPBadValueException()
	{
		super();
	}
	
	
	/**
	*		Cria uma excess�o com uma mensagem de string
	*/
	
	public SNMPBadValueException(String s)
	{
		super(s);
	}
	
}
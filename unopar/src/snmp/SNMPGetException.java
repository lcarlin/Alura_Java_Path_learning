/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPGetException.java
 * Pacote snmp
 * Fun��o excess�o lan�ada quando a tentativa de extrair o valor
 *       de um OID SNMP falha. A raz�o poderia ser que a variavel especifica n�o � 
 *       suportada pelo dispositivo, ou que o nome da comunidade fornecido tem 
 *       privil�gios insulficientes
 * Data  14/10/2002
 * Modifica��o 
 ****************************************************************/



package snmp;


/**
*	Exception thrown when attempt to get value of SNMP OID from device fails. Reason could be that 
*	specified variable not supported by device, or that supplied community name has insufficient
*	privileges.
*/

public class SNMPGetException extends Exception
{
	
	public SNMPGetException()
	{
		super();
	}
	
	
	/**
	*	Cria uma excess�o com uma mensagem de string
	*/
	
	public SNMPGetException(String s)
	{
		super(s);
	}
	
}
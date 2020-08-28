/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPGetException.java
 * Pacote snmp
 * Função excessão lançada quando a tentativa de extrair o valor
 *       de um OID SNMP falha. A razão poderia ser que a variavel especifica não é 
 *       suportada pelo dispositivo, ou que o nome da comunidade fornecido tem 
 *       privilégios insulficientes
 * Data  14/10/2002
 * Modificação 
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
	*	Cria uma excessão com uma mensagem de string
	*/
	
	public SNMPGetException(String s)
	{
		super(s);
	}
	
}
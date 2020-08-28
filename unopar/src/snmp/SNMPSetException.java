/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPSetException.java
 * Pacote snmp
 * Fun��o excess�o lan�ada quando a tentativa de configurar o valor
 *       de um OID SNMp falha. A raz�o poderia ser que a variavel especifica n�o � 
 *       suportada pelo dispositivo, ou que o nome da comunidade fornecido tem 
 *       privil�gios insulficientes
 * Data  14/10/2002
 * Modifica��o 
 ****************************************************************/


package snmp;



public class SNMPSetException extends Exception
{
	
	public SNMPSetException()
	{
		super();
	}
	
	
	/**
	*   Cria uma excess�o com uma mensagem de string
	*/
	
	public SNMPSetException(String s)
	{
		super(s);
	}
	
}
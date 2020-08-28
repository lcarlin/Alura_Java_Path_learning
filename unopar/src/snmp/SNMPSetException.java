/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPSetException.java
 * Pacote snmp
 * Função excessão lançada quando a tentativa de configurar o valor
 *       de um OID SNMp falha. A razão poderia ser que a variavel especifica não é 
 *       suportada pelo dispositivo, ou que o nome da comunidade fornecido tem 
 *       privilégios insulficientes
 * Data  14/10/2002
 * Modificação 
 ****************************************************************/


package snmp;



public class SNMPSetException extends Exception
{
	
	public SNMPSetException()
	{
		super();
	}
	
	
	/**
	*   Cria uma excessão com uma mensagem de string
	*/
	
	public SNMPSetException(String s)
	{
		super(s);
	}
	
}
/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPBadValueException.java
 * Pacote snmp
 * Função excessão lançada quando tenta criar uma subclase SNMPObject
 *        com um dado inapropriado, ou configurar seus valores com dados
 *        inapropriados
 * Data  14/10/2002
 * Modificação 
 ****************************************************************/



package snmp;


public class SNMPBadValueException extends Exception
{
	
	public SNMPBadValueException()
	{
		super();
	}
	
	
	/**
	*		Cria uma excessão com uma mensagem de string
	*/
	
	public SNMPBadValueException(String s)
	{
		super(s);
	}
	
}